package com.edu.fpt.hoursemanager.management.directory.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import com.edu.fpt.hoursemanager.management.account.entity.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Directory extends EntityCommon {

    private String name;
    private String url;
    private String nameIcon;

    public Directory(String name) {
        this.name = name;
    }

    public Directory(String name, Collection<Role> roles) {
        this.name = name;
        this.roles = roles;
    }

    public Directory(String name, Collection<Role> roles, MainDirectory mainDirectory) {
        this.name = name;
        this.roles = roles;
        this.mainDirectory = mainDirectory;
    }

    public Directory(Collection<Role> roles, MainDirectory mainDirectory) {
        this.name = name;
        this.roles = roles;
        this.mainDirectory = mainDirectory;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "directory_role",
            joinColumns = @JoinColumn(name = "directory_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private MainDirectory mainDirectory;
}
