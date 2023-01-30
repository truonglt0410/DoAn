package com.edu.fpt.hoursemanager.management.room.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import com.edu.fpt.hoursemanager.management.assets.entity.Assets;
import com.edu.fpt.hoursemanager.management.building.entity.Building;
import com.edu.fpt.hoursemanager.management.contract.entity.RenterRoom;
import com.edu.fpt.hoursemanager.management.roomservice.entity.RoomService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room extends EntityCommon {
    private String name;
    private String codeTypeRoom;
    private String roomImage;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Collection<RenterRoom> renterRooms;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Collection<Assets> assets;

    @ManyToOne
    @JoinColumn(name = "type_rooms_id")
    private TypeRoom typeRooms;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "room")
    private Collection<RoomService> roomServices;
}
