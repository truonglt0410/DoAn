package com.edu.fpt.hoursemanager.management.directory.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainDirectory extends EntityCommon {

    private String name;
    private String imageName;

    public MainDirectory(String name) {
        this.name = name;
    }

    public MainDirectory(String name, Collection<Directory> directories) {
        this.name = name;
        this.directories = directories;
    }

    public MainDirectory(String name, String imageName) {
        this.name = name;
        this.imageName = imageName;
    }

    @OneToMany(mappedBy = "mainDirectory", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Directory> directories;
}
