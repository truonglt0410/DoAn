package com.edu.fpt.hoursemanager.management.roomservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomServiceEWResponse {

    private Long idRoom;
    private Long idRoomService;
    private String nameRoom;
    private double amount;
    private Date dateIncome;
    private String nameBuilding;
}
