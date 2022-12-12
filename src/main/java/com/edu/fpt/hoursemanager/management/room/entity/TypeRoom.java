package com.edu.fpt.hoursemanager.management.room.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeRoom extends EntityCommon {
    private String name;
    private Long price;
    private String capacity;
    private String description;
    private String roomArea;
    private String deposit;

    @OneToMany(mappedBy = "typeRooms", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Room> rooms;
}
