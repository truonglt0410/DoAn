package com.edu.fpt.hoursemanager.management.roomservice.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import com.edu.fpt.hoursemanager.management.building.entity.Building;
import com.edu.fpt.hoursemanager.management.room.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service extends EntityCommon {
    private String name;
    private Double price;
    private String typePayment;
    private boolean isElectric;

    @OneToMany(mappedBy = "service")
    private Collection<RoomService> roomServices;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
}
