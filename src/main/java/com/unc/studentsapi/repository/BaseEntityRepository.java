package com.unc.studentsapi.repository;

import com.unc.studentsapi.entity.baseEntity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseEntityRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {
    default T findByIdOrThrow(T t) {
        Optional<T> optionalT =  findById(t.id);
        if (optionalT.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return optionalT.get();
    }

    List<T> findByEntityMetadataClonedFrom(UUID clonedFrom);
    default void deleteByIdWithoutThrowing(UUID id) {
        try {
            deleteById(id);
        } catch (Exception ignored) {}
    }
}
