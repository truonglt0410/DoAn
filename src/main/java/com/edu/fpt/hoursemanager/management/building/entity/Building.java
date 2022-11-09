package com.edu.fpt.hoursemanager.management.building.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import com.edu.fpt.hoursemanager.management.account.entity.Account;
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
public class Building extends EntityCommon {
    private String name;
    private String address;
    private String image;
    private String description;
    private String utilities;
    private String rules;
    private String rulesImage;

    public Building(String name, String address, String image, String description, String utilities, String rules) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.description = description;
        this.utilities = utilities;
        this.rules = rules;
    }

    public Building(String name, String address, String image, String description, String utilities, String rules, String rulesImage) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.description = description;
        this.utilities = utilities;
        this.rules = rules;
        this.rulesImage = rulesImage;
    }

    @ManyToMany(mappedBy = "buildings")
    private Collection<Account> accounts;

}
