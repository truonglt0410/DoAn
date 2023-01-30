package com.edu.fpt.hoursemanager.management.assets.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeAssets extends EntityCommon {
    private String name;
    private String code;
    private Long idBuilding;

    @OneToMany(mappedBy = "typeAssets",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Collection<Assets> assets;
}
