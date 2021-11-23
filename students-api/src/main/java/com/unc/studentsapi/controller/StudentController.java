package com.unc.studentsapi.controller;

import com.unc.studentsapi.entity.student.Student;
import com.unc.studentsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity<Student> create() {
        return new ResponseEntity<>(studentService.create(), HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Student> find(String studentId) {
        return new ResponseEntity<>(studentService.find(UUID.fromString(studentId)), HttpStatus.OK);
    }

    @PutMapping("/activate")
    public ResponseEntity<Student> activate(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.activate(student), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<Student>> findAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            Student student
    ) {
        return new ResponseEntity<>(studentService.findAll(page, size, student), HttpStatus.OK);
    }

    @PutMapping("/create-inactive-clone")
    public ResponseEntity<Student> createInactiveClone(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.createInactiveClone(student), HttpStatus.OK);
    }
}
