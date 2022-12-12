package com.edu.fpt.hoursemanager.management.account.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import com.edu.fpt.hoursemanager.management.directory.entity.Directory;
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
public class Role extends EntityCommon {
    private String code;
    private String name;

    public Role(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    private Collection<Account> accounts;

    @ManyToMany(mappedBy = "roles")
    private Collection<Directory> directories;
}
