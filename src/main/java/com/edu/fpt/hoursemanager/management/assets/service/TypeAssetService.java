package com.edu.fpt.hoursemanager.management.assets.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.assets.model.request.TypeAssetRequest;
import org.springframework.http.ResponseEntity;

public interface TypeAssetService {
    ResponseEntity<ResponseModels> getAllTypeAssets();

    ResponseEntity<ResponseModels> getTypeAssetsByBuilding(Long id);

    ResponseEntity<ResponseModels> createTypeAssets(TypeAssetRequest request);

    ResponseEntity<ResponseModels> deleteTypeAsset(Long id);

    ResponseEntity<ResponseModels> updateTypeAsset(TypeAssetRequest typeAssetRequest);
}
