package com.unc.studentsapi.repository;

import com.unc.studentsapi.entity.student.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(isolation = Isolation.SERIALIZABLE)
@Primary
public interface StudentRepository extends BaseEntityRepository<Student> {
//
//    default Student findByIdOrThrow(Student student) {
//        Optional<Student> optionalStudent =  findById(student.studentId);
//        if (optionalStudent.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        return optionalStudent.get();
//    }
//
//    default Student findByIdOrThrow(UUID studentId) {
//        Optional<Student> optionalStudent =  findById(studentId);
//        if (optionalStudent.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        return optionalStudent.get();
//    }
//
//    List<Student> findByEntityMetadataClonedFrom(UUID clonedFrom);
}
