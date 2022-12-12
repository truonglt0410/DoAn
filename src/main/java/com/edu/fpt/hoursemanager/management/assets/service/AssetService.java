package com.edu.fpt.hoursemanager.management.assets.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.assets.entity.Assets;
import com.edu.fpt.hoursemanager.management.assets.model.request.AssetRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssetService {
    ResponseEntity<ResponseModels> getAllAssets();

    ResponseEntity<ResponseModels> addAsset(AssetRequest assetRequest);

    ResponseEntity<ResponseModels> updateAsset(AssetRequest assetRequest);

    ResponseEntity<ResponseModels> deleteAsset(Long id);

    ResponseEntity<ResponseModels> getAllAssetsNotRoom();
}
