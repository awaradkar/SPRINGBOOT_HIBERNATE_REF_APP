package com.dit.app.demo.model;

import com.dit.app.demo.config.ZonedateTimeConverter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "ID_GENERATOR")
public class IdGenerator {

    @Id
    @Column(length = 10)
    private String idKey;

    @Column(precision = 10)
    private BigDecimal idValue;

    @Convert(converter = ZonedateTimeConverter.class)
    private ZonedDateTime createdDate;

    @Column(length = 20)
    private String createdBy;

    @Convert(converter = ZonedateTimeConverter.class)
    private ZonedDateTime modifiedDate;

    @Column(length = 20)
    private String modifiedBy;

    public String getIdKey() {
        return idKey;
    }

    public void setIdKey(String idKey) {
        this.idKey = idKey;
    }

    public BigDecimal getIdValue() {
        return idValue;
    }

    public void setIdValue(BigDecimal idValue) {
        this.idValue = idValue;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
