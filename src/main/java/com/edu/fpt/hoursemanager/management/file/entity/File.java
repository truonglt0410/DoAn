package com.edu.fpt.hoursemanager.management.file.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private LocalDate created_date = LocalDate.now() ;

    private String created_by;

    private LocalDate modified_date = LocalDate.now() ;

    private String modified_by;

    private Boolean deleted = false;

    private String name;

    private String type;

    private String url;
}
