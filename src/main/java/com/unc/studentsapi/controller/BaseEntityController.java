package com.unc.studentsapi.controller;

import com.unc.studentsapi.entity.baseEntity.BaseEntity;
import com.unc.studentsapi.repository.BaseEntityRepository;
import com.unc.studentsapi.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public class BaseEntityController<T extends BaseEntity> {

    @Autowired
    EntityService<T, BaseEntityRepository<T>> entityService;

    public ResponseEntity<T> baseEntityControllerCreate(T t) {
        return new ResponseEntity<>(entityService.create(t), HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<T> save(@RequestBody T t) {
        return new ResponseEntity<>(entityService.save(t), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<T> find(String id) {
        return new ResponseEntity<>(entityService.find(UUID.fromString(id)), HttpStatus.OK);
    }

    @PutMapping("/activate")
    public ResponseEntity<T> activate(@RequestBody T t) {
        return new ResponseEntity<>(entityService.activate(t), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<T>> findAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            T t
    ) {
        System.out.println(t);
        return new ResponseEntity<>(entityService.findAll(page, size, t), HttpStatus.OK);
    }

    @PutMapping("/create-inactive-clone")
    public ResponseEntity<T> createInactiveClone(@RequestBody T t) {
        return new ResponseEntity<>(entityService.createInactiveClone(t), HttpStatus.OK);
    }
}
