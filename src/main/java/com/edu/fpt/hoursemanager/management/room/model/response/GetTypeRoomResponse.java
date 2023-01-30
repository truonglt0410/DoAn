package com.edu.fpt.hoursemanager.management.room.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTypeRoomResponse {
    private Long idTypeRoom;
    private String nameTypeRoom;
    private Long priceTypeRoom;
    private String capacityTypeRoom;
    private String descriptionTypeRoom;
    private String roomAreaTypeRoom;
    private String depositTypeRoom;
    private Long idBuilding;
}
