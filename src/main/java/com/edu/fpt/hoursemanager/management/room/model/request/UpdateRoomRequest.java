package com.edu.fpt.hoursemanager.management.room.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoomRequest {
    private Long idRoom;
    private String nameRoom;
    private String roomImage;
    private String codeTypeRoom;
    private Long idTypeRoom;
    private Long idBuilding;
}
