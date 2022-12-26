package com.edu.fpt.hoursemanager.management.assets.model.response;

import java.util.List;

import com.edu.fpt.hoursemanager.management.assets.entity.Assets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeAssetsResponseApi {
    private Long idTypeAsset;
    private String nameTypeAsset;
    private String codeTypeAsset;
    private List<AssetResponse> assets;
}
