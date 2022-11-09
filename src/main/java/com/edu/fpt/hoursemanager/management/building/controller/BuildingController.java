package com.edu.fpt.hoursemanager.management.building.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.building.model.form.CreateBuildingForm;
import com.edu.fpt.hoursemanager.management.building.model.form.UpdateBuildingForm;
import com.edu.fpt.hoursemanager.management.building.model.response.BuildingDetailResponse;
import com.edu.fpt.hoursemanager.management.building.model.response.BuildingResponse;
import com.edu.fpt.hoursemanager.management.building.service.BuildingManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/building")
public class BuildingController {
    @Autowired
    private BuildingManageService buildingManageService;

    //************************************
    // Find Building by Account id
    //************************************
    @GetMapping("/getAllBuildingByAccountId")
    public ResponseEntity<ResponseModels> getAllBuildingByAccountById(@RequestParam Long id) {
        List<BuildingResponse> buildingList = buildingManageService.listAllBuildingByAccountId(id);
        if (buildingList == null || buildingList.isEmpty()) {
            return ResponseModels.success("Not Building Has Found.");
        }
        return ResponseModels.success(buildingList);
    }

    //************************************
    // Find Building by Account id
    //************************************
    @GetMapping("/getDetailBuildingById")
    public ResponseEntity<ResponseModels> getDetailBuildingById(@RequestParam Long id) {
        BuildingDetailResponse building = buildingManageService.getDetailBuildingById(id);
        if (building == null) {
            return ResponseModels.success("Not Building Has Found.");
        }
        return ResponseModels.success(building);
    }

    //************************************
    // Add new Building
    //************************************
    @PostMapping("/add")
    public ResponseEntity<ResponseModels> addBuilding(@RequestBody CreateBuildingForm createBuildingForm) {
        boolean checkAdd = buildingManageService.addBuilding(createBuildingForm);
        if(checkAdd){
            return ResponseModels.created("Add new Building Success.");
        }
        return ResponseModels.created("Add new Building Fail. Building is Exist.");
    }

    //************************************
    // Update Building
    //************************************
    @PostMapping("/update")
    public ResponseEntity<ResponseModels> updateBuilding(@RequestBody UpdateBuildingForm updateBuildingForm) {
        boolean checkUpdate = buildingManageService.updateBuilding(updateBuildingForm);
        if(checkUpdate){
            return ResponseModels.created("Update Building Success.");
        }
        return ResponseModels.created("Update Building Fail. Building is not Exist.");
    }

    //************************************
    // Delete Building
    //************************************
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModels> deleteBuilding(@RequestParam Long id) {
        boolean checkDelete = buildingManageService.deleteBuilding(id);
        if(checkDelete){
            return ResponseModels.success("Delete Successful.");
        }
        return ResponseModels.success("Delete Fail.");
    }


}
