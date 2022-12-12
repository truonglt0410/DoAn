package com.edu.fpt.hoursemanager.management.building.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.building.entity.Building;
import com.edu.fpt.hoursemanager.management.building.model.request.CreateBuildingRequest;
import com.edu.fpt.hoursemanager.management.building.model.request.UpdateBuildingRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BuildingManageService {
    Building saveBuilding(Building building);

    List<Building> saveBuilding(List<Building> buildings);

    ResponseEntity<ResponseModels> listAllBuildingByAccountId();

    ResponseEntity<ResponseModels> getDetailBuildingById(Long id);

    ResponseEntity<ResponseModels> addBuilding(CreateBuildingRequest createBuildingRequest);

    ResponseEntity<ResponseModels> updateBuilding(UpdateBuildingRequest updateBuildingRequest);

    ResponseEntity<ResponseModels> deleteBuilding(Long id);
}
