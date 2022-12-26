package com.edu.fpt.hoursemanager.management.room.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailRoomResponseFromQuery {
    private Long id;
    private String roomName;
    private String typeRoomName;
    private String capacity;
    private String deposit;
    private long price;
    private String roomArea;
    private String buildingName;
    private String assetsName;
    private String color;
    private String model;
}

