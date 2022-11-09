package com.edu.fpt.hoursemanager.management.building.service;

import com.edu.fpt.hoursemanager.management.building.entity.Building;
import com.edu.fpt.hoursemanager.management.building.model.form.CreateBuildingForm;
import com.edu.fpt.hoursemanager.management.building.model.form.UpdateBuildingForm;
import com.edu.fpt.hoursemanager.management.building.model.response.BuildingDetailResponse;
import com.edu.fpt.hoursemanager.management.building.model.response.BuildingResponse;

import java.util.List;

public interface BuildingManageService {
    Building saveBuilding(Building building);

    List<Building> saveBuilding(List<Building> buildings);

    List<BuildingResponse> listAllBuildingByAccountId(Long id);

    BuildingDetailResponse getDetailBuildingById(Long id);

    Boolean addBuilding(CreateBuildingForm createBuildingForm);

    Boolean updateBuilding(UpdateBuildingForm updateBuildingForm);

    Boolean deleteBuilding(Long id);
}
