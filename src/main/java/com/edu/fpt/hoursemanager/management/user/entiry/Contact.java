package com.edu.fpt.hoursemanager.management.user.entiry;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends EntityCommon {
    private String name;
}
