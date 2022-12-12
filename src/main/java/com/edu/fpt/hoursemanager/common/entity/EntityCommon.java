package com.edu.fpt.hoursemanager.common.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Data
@MappedSuperclass
public class EntityCommon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate createdDate = LocalDate.now() ;

    private String createdBy;

    private LocalDate modifiedDate = LocalDate.now() ;

    private String modifiedBy;

    private Boolean deleted = false;
}
