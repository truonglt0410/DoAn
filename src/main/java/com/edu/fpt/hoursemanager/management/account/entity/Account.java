package com.edu.fpt.hoursemanager.management.account.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import com.edu.fpt.hoursemanager.common.enums.ProviderAccount;
import com.edu.fpt.hoursemanager.management.building.entity.Building;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends EntityCommon {
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private ProviderAccount authProvider;

    public Account(String email, String password, ProviderAccount authProvider) {
        this.email = email;
        this.password = password;
        this.authProvider = authProvider;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "account_building",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "building_id", referencedColumnName = "id")
    )
    private Collection<Building> buildings;
    
}
