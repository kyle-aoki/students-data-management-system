package com.unc.studentsapi.controller;

import com.unc.studentsapi.entity.student.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/student")
@RestController
public class StudentController extends BaseEntityController<Student> {

    @PostMapping("/create")
    public ResponseEntity<Student> create() {
        return this.baseEntityControllerCreate(new Student(true));
    }

}
