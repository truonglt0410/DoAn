package com.edu.fpt.hoursemanager.management.assets.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetWarehouseResponse {
    private Long idWarehouse;
    private String nameWarehouse;

    private List<AssetResponse> assetResponses;


}
