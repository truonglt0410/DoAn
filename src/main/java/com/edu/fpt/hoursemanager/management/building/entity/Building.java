package com.edu.fpt.hoursemanager.management.building.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import com.edu.fpt.hoursemanager.management.account.entity.Account;
import com.edu.fpt.hoursemanager.management.room.entity.Room;
import com.edu.fpt.hoursemanager.management.roomservice.entity.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Building extends EntityCommon {
    private String name;
    private String address;
    private String image;
    private String description;
    private String rulesImage;
    private Double longitude;
    private Double latitude;

    public Building(String name, String address, String image, String description) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.description = description;
    }

    public Building(String name, String address, String image, String description, String rulesImage) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.description = description;
        this.rulesImage = rulesImage;
    }

    @ManyToMany(mappedBy = "buildings")
    private Collection<Account> accounts;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "building_utilities",
            joinColumns = @JoinColumn(name = "building_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "utilities_id", referencedColumnName = "id")
    )
    private Collection<Utilities> utilities;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private Collection<Room> rooms;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private Collection<Service> services;
}
