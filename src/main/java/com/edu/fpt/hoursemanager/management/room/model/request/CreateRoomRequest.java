package com.edu.fpt.hoursemanager.management.room.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomRequest {
    private String name;
    private String typeRoom;
    private Long typeRoomId;
    private Long buildingId;
}
