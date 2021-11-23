package com.unc.studentsapi.entity.student;

import com.unc.studentsapi.entity.entityMetadata.EntityMetadata;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    public UUID studentId;

    @Embedded
    public EntityMetadata entityMetadata;

    @Embedded
    @Valid
    public Name name;

    public Date birthday;

    public Date enrollmentDate;

    public Student() {}
    public Student(Boolean newEntity) {
        this.studentId = UUID.randomUUID();
        this.name = new Name();
        this.entityMetadata = new EntityMetadata();
    }

}
