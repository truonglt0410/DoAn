package com.edu.fpt.hoursemanager.management.assets.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetResponse {
    private Long idAsset;
    private String nameAsset;
    private String colorAsset;
    private String statusAsset;
    private String modelAsset;
    private Long idTypeAsset;

}
