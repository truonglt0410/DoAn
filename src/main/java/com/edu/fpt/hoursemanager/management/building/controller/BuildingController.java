package com.edu.fpt.hoursemanager.management.building.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.building.model.request.CreateBuildingRequest;
import com.edu.fpt.hoursemanager.management.building.model.request.UpdateBuildingRequest;
import com.edu.fpt.hoursemanager.management.building.service.BuildingManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/building")
public class BuildingController {
    @Autowired
    private BuildingManageService buildingManageService;

    //************************************
    // Find Building by Account id
    //************************************
    @GetMapping("/get-all-by-account-id")
    public ResponseEntity<ResponseModels> getAllBuildingByAccountById() {
        return buildingManageService.listAllBuildingByAccountId();
    }

    //************************************
    // Find Building by Account id
    //************************************
    @GetMapping("/get-detail-by-id")
    public ResponseEntity<ResponseModels> getDetailBuildingById(@RequestParam Long id) {
        return buildingManageService.getDetailBuildingById(id);
    }

    //************************************
    // Add new Building
    //************************************
    @PostMapping("/add")
    public ResponseEntity<ResponseModels> addBuilding(@RequestBody CreateBuildingRequest createBuildingRequest) {
        return buildingManageService.addBuilding(createBuildingRequest);
    }

    //************************************
    // Update Building
    //************************************
    @PostMapping("/update")
    public ResponseEntity<ResponseModels> updateBuilding(@RequestBody UpdateBuildingRequest updateBuildingRequest) {
        return buildingManageService.updateBuilding(updateBuildingRequest);
    }

    //************************************
    // Delete Building
    //************************************
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModels> deleteBuilding(@RequestParam Long id){
        return buildingManageService.deleteBuilding(id);
    }


}
