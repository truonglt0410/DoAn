package com.edu.fpt.hoursemanager.management.room.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAssetToRoomRequest {
    private Long roomId;
    private List<Long> assetId;
}
