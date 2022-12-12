package com.edu.fpt.hoursemanager.management.roomservice.entity;

import com.edu.fpt.hoursemanager.common.entity.EntityCommon;
import com.edu.fpt.hoursemanager.management.room.entity.Room;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomService extends EntityCommon {
    private String name;
    private Date fromDate;
    private Date toDate;
    private Boolean status;
    private double amount;
    private String note;
    private double total;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "service_id")
    private Service service;
}
