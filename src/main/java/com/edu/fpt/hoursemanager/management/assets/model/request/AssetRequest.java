package com.edu.fpt.hoursemanager.management.assets.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetRequest {
    private Long id;
    private String name;
    private String color;
    private String status;
    private String model;
//    private Long idWarehouse;
    private Long idTypeAssets;
    private Long idRoom;
}
