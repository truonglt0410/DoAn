package com.edu.fpt.hoursemanager.management.room.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailRoomResponseApi {
    private Long id;
    private String roomName;
    private String imageRoom;
    private List<DetailAssetsResponse> assets;
    private String buildingName;
    private String typeRoomName;
    private String capacity;
    private String deposit;
    private long price;
    private String roomArea;
}
