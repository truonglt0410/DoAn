package com.edu.fpt.hoursemanager.management.assets.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import com.edu.fpt.hoursemanager.management.issue.entity.Issue;
import com.edu.fpt.hoursemanager.management.room.entity.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Assets extends EntityCommon {
    private String name;
    private String color;
    private String status;
    private String model;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "room_id")
    private Room room;

//    @ManyToOne
//    @JoinColumn(name = "warehouse_id")
//    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "type_asset_id")
    private TypeAssets typeAssets;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Issue> issue;
}
