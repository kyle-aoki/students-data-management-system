package com.unc.studentsapi.entity.student;

import com.unc.studentsapi.validation.BaseValidationGroup;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Embeddable
@Data
public class Name {

    @NotBlank(groups = BaseValidationGroup.class)
    public String firstName;

    @NotBlank(groups = BaseValidationGroup.class)
    public String lastName;

    public Name() {}
    public Name(Boolean newEntity) {
        this.firstName = "";
        this.lastName = "";
    }
}
