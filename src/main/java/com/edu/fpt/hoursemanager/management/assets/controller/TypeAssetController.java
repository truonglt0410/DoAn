package com.edu.fpt.hoursemanager.management.assets.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.assets.model.request.TypeAssetRequest;
import com.edu.fpt.hoursemanager.management.assets.service.TypeAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/asset/type")
public class TypeAssetController {
    @Autowired
    TypeAssetService typeAssetService;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseModels> getAllTypeAssets()  {
        return typeAssetService.getAllTypeAssets();
    }

    @GetMapping("/get-by-building")
    public ResponseEntity<ResponseModels> getTypeAssetsByBuilding(@RequestParam Long id)  {
        return typeAssetService.getTypeAssetsByBuilding(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseModels> createTypeAssets(@RequestBody TypeAssetRequest request) {
        return typeAssetService.createTypeAssets(request);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModels> deleteTypeAsset(@RequestParam Long id) {
        return typeAssetService.deleteTypeAsset(id);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseModels> updateTypeAssets(@RequestBody TypeAssetRequest request) {
        return typeAssetService.updateTypeAsset(request);
    }


}
