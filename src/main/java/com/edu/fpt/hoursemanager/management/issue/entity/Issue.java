package com.edu.fpt.hoursemanager.management.issue.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import com.edu.fpt.hoursemanager.management.assets.entity.Assets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue extends EntityCommon {
    private String name;
    private String status;
    private String price;
    private String description;
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    Assets asset;
}
