package com.edu.fpt.hoursemanager.management.assets.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse extends EntityCommon {
    private String name;

//    @OneToMany(mappedBy = "warehouse", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Collection<Assets> asset;
}
