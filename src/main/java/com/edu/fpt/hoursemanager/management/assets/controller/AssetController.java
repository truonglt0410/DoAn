package com.edu.fpt.hoursemanager.management.assets.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.assets.model.request.AssetRequest;
import com.edu.fpt.hoursemanager.management.assets.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/asset")
public class AssetController {
    @Autowired
    AssetService assetService;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseModels> getAllAssets() {
        return assetService.getAllAssets();
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseModels> addAsset(@RequestBody AssetRequest assetRequest) {
        return assetService.addAsset(assetRequest);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseModels> updateAsset(@RequestBody AssetRequest assetRequest) {
        return assetService.updateAsset(assetRequest);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModels> deleteAsset(@RequestParam Long id) {
        return assetService.deleteAsset(id);
    }

    @GetMapping("/get-all-not-room")
    public ResponseEntity<ResponseModels> getAllAssetsNotRoom(){
        return assetService.getAllAssetsNotRoom();
    }

    @GetMapping("/get-asset-by-room")
    public ResponseEntity<ResponseModels> getAssetsByRoom(@RequestParam Long id) {
        return assetService.getAssetByRoom(id);
    }
}
