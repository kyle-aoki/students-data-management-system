package com.unc.studentsapi.entity.student;

import com.unc.studentsapi.entity.baseEntity.BaseEntity;
import com.unc.studentsapi.entity.entityMetadata.EntityMetadata;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Embedded
    @Valid
    public Name name;

    public Date birthday;

    public Date enrollmentDate;

    public Student() {}
    public Student (Boolean newEntity) {
        this.id = UUID.randomUUID();
        this.name = new Name(newEntity);
        this.entityMetadata = new EntityMetadata(newEntity);
    }
}
