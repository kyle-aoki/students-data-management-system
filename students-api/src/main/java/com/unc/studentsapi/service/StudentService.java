package com.unc.studentsapi.service;

import com.unc.studentsapi.entity.entityMetadata.EntityStatus;
import com.unc.studentsapi.entity.student.Student;
import com.unc.studentsapi.validation.BaseValidationGroup;
import com.unc.studentsapi.repository.StudentRepository;
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
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    Validator validator;

    @Autowired
    EntityManager entityManager;

    ExampleMatcher exampleMatcher = ExampleMatcher
            .matchingAll()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    public Student create() {
        Student newStudent = new Student(true);
        return studentRepository.save(newStudent);
    }

    public Student save(Student student) {
        if (student.entityMetadata.entityStatus == EntityStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot activate with /save route.");
        }
        Set<ConstraintViolation<Student>> violations = validator.validate(student, BaseValidationGroup.class);
        student.entityMetadata.setValid(violations.isEmpty());

        return studentRepository.save(student);
    }

    public Student find(UUID studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optionalStudent.get();
    }

    public Student activate(Student student) {
        Set<ConstraintViolation<Student>> violations = validator.validate(student, BaseValidationGroup.class);
        if (!violations.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entity not valid.");
        student.entityMetadata.setValid(true);

        if (student.entityMetadata.entityStatus == EntityStatus.INACTIVE_CLONE) {
            student.studentId = student.entityMetadata.clonedFrom;
            student.entityMetadata.entityStatus = EntityStatus.ACTIVE;
            student.entityMetadata.clonedFrom = null;
            return studentRepository.save(student);
        }

        student.entityMetadata.entityStatus = EntityStatus.ACTIVE;
        return studentRepository.save(student);
    }

    public Page<Student> findAll(Integer page, Integer size, Student student) {
        Example<Student> studentExample = Example.of(student, exampleMatcher);
        return studentRepository.findAll(studentExample, PageRequest.of(page, size));
    }

    public Student createInactiveClone(Student student) {
        List<Student> students = studentRepository.findByEntityMetadataClonedFrom(student.studentId);
        if (students.size() >= 1) return students.get(0);

        Student currentStudent = studentRepository.findByIdOrThrow(student);
        UUID parentId = currentStudent.studentId;

        // Must Detach Other Embedded Entities As Well
        entityManager.detach(currentStudent);

        currentStudent.setStudentId(UUID.randomUUID());
        currentStudent.entityMetadata.setEntityStatus(EntityStatus.INACTIVE_CLONE);
        currentStudent.entityMetadata.setClonedFrom(parentId);
        return studentRepository.save(currentStudent);
    }

}
