package com.unc.studentsapi.entity.entityMetadata;

import lombok.Data;

import javax.persistence.Embeddable;
import java.util.Date;
import java.util.UUID;

@Embeddable
@Data
public class EntityMetadata {

    public EntityStatus entityStatus;
    public Date createdTimestamp;
    public Boolean valid;

    public Date lastEditedTimestamp;
    public UUID clonedFrom;

    public EntityMetadata() {
        this.entityStatus = EntityStatus.PROVISIONAL;
        this.createdTimestamp = new Date();
        this.valid = false;
    }
}
