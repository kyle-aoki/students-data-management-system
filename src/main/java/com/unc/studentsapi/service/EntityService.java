package com.unc.studentsapi.service;

import com.unc.studentsapi.entity.baseEntity.BaseEntity;
import com.unc.studentsapi.entity.entityMetadata.EntityStatus;
import com.unc.studentsapi.repository.BaseEntityRepository;
import com.unc.studentsapi.validation.BaseValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

@Service
public class EntityService<Entity extends BaseEntity, EntityRepository extends BaseEntityRepository<Entity>> {

    @Autowired
    EntityRepository entityRepository;

    @Autowired
    Validator validator;

    @Autowired
    EntityManager entityManager;

    ExampleMatcher exampleMatcher = ExampleMatcher
            .matchingAll()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    public Entity create(Entity entity) {
        return entityRepository.save(entity);
    }

    public Entity save(Entity entity) {
        if (entity.entityMetadata.entityStatus == EntityStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot activate with /save route.");
        }
        Set<ConstraintViolation<Entity>> violations = validator.validate(entity, BaseValidationGroup.class);
        entity.entityMetadata.setValid(violations.isEmpty());

        return entityRepository.save(entity);
    }

    public Entity find(UUID id) {
        Optional<Entity> optionalT = entityRepository.findById(id);
        if (optionalT.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optionalT.get();
    }

    public Entity activate(Entity entity) {
        Set<ConstraintViolation<Entity>> violations = validator.validate(entity, BaseValidationGroup.class);
        if (!violations.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entity not valid.");
        entity.entityMetadata.setValid(true);

        if (entity.entityMetadata.entityStatus == EntityStatus.INACTIVE_CLONE) {
            UUID cloneId = entity.id;
            entity.setId(entity.entityMetadata.clonedFrom);
            entity.entityMetadata.setEntityStatus(EntityStatus.ACTIVE);
            entity.entityMetadata.clonedFrom = null;

            entityRepository.deleteByIdWithoutThrowing(cloneId);
            return entityRepository.save(entity);
        }

        entity.entityMetadata.setEntityStatus(EntityStatus.ACTIVE);
        return entityRepository.save(entity);
    }

    public Page<Entity> findAll(Integer page, Integer size, Entity entity) {
        Example<Entity> tExample = Example.of(entity, exampleMatcher);
        return entityRepository.findAll(tExample, PageRequest.of(page, size));
    }

    public Entity createInactiveClone(Entity entity) {
        List<Entity> entities = entityRepository.findByEntityMetadataClonedFrom(entity.id);
        if (entities.size() >= 1) return entities.get(0);

        Entity currentEntity = entityRepository.findByIdOrThrow(entity);
        UUID parentId = currentEntity.id;

        // Must Detach Other Embedded Entities As Well
        entityManager.detach(currentEntity);

        currentEntity.setId(UUID.randomUUID());
        currentEntity.entityMetadata.setEntityStatus(EntityStatus.INACTIVE_CLONE);
        currentEntity.entityMetadata.setClonedFrom(parentId);
        return entityRepository.save(currentEntity);
    }

}
