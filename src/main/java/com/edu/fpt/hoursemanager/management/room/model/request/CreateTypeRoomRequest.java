package com.edu.fpt.hoursemanager.management.room.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTypeRoomRequest {
    private String name;
    private Long price;
    private String capacity;
    private String description;
    private String roomArea;
    private String deposit;
    private String rooms;
    private Long idBuilding;
}
