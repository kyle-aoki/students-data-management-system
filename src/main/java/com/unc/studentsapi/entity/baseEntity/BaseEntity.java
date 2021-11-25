package com.unc.studentsapi.entity.baseEntity;

import com.unc.studentsapi.entity.entityMetadata.EntityMetadata;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class BaseEntity {
    @Id
    public UUID id;

    @Embedded
    public EntityMetadata entityMetadata;

}
