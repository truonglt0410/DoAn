package com.edu.fpt.hoursemanager.management.room.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomByBuildingAndContact {
    private Long idRoom;
    private Long codeTypeRoom;
    private String capacity;
    private String nameRoom;
    private Long idBuilding;
    private String buildingName;
    private Long price;
    private int status;
}
