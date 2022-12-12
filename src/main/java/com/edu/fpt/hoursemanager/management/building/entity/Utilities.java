package com.edu.fpt.hoursemanager.management.building.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilities extends EntityCommon {
    private String name;
    private boolean type;

    @ManyToMany(mappedBy = "utilities")
    private Collection<Building> buildings;
}
