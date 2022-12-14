package com.edu.fpt.hoursemanager.management.room.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomBuildingResponse {
    private Long idRoom;
    private  Long codeTypeRoom;
    private String capacity;
    private String nameRoom;
    private String roomImage;
    private Long idBuilding;
    private String buildingName;
    private Long contactId;
    private Long price;
}
