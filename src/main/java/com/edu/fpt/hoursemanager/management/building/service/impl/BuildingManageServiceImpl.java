package com.edu.fpt.hoursemanager.management.building.service.impl;

import com.edu.fpt.hoursemanager.management.account.entity.Account;
import com.edu.fpt.hoursemanager.management.account.service.AccountService;
import com.edu.fpt.hoursemanager.management.building.entity.Building;
import com.edu.fpt.hoursemanager.management.building.model.form.CreateBuildingForm;
import com.edu.fpt.hoursemanager.management.building.model.form.UpdateBuildingForm;
import com.edu.fpt.hoursemanager.management.building.model.response.BuildingDetailResponse;
import com.edu.fpt.hoursemanager.management.building.model.response.BuildingResponse;
import com.edu.fpt.hoursemanager.management.building.repository.BuildingManageRepository;
import com.edu.fpt.hoursemanager.management.building.service.BuildingManageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class BuildingManageServiceImpl implements BuildingManageService {
    @Autowired
    private BuildingManageRepository buildingManageRepository;
    @Autowired
    private AccountService accountService;
    @Override
    public Building saveBuilding(Building building) {
        return buildingManageRepository.save(building);
    }

    @Override
    public List<Building> saveBuilding(List<Building> buildings) {
        return buildingManageRepository.saveAll(buildings);
    }

    @Override
    public List<BuildingResponse> listAllBuildingByAccountId(Long id) {
        List<BuildingResponse> list = null;
        try {
            list = buildingManageRepository.getAllBuildingByAccountId(id);
        }catch (Exception e){
            throw new RuntimeException("Error Building  Manage Repository" + e.getMessage());
        }
        return list;
    }

    @Override
    public BuildingDetailResponse getDetailBuildingById(Long id) {
        BuildingDetailResponse building = null;
        try{
            building = buildingManageRepository.getDetailBuildingById(id);
        }catch (Exception e){
            throw new RuntimeException("Error Building Manage Repository" + e.getMessage());
        }
        return building;
    }

    @Override
    public Boolean addBuilding(CreateBuildingForm createBuildingForm) {
        boolean addStatus = false;
        try{
            String checkBuildingExist = buildingManageRepository.checkBuildingExist(createBuildingForm.getName());
            if(StringUtils.isEmpty(checkBuildingExist)){
                Building building = new Building();
                building.setName(createBuildingForm.getName());
                building.setAddress(createBuildingForm.getAddress()+ " " + createBuildingForm.getWards()+ " "  + createBuildingForm.getDistrict()+ " "  + createBuildingForm.getCity());
                building.setImage(createBuildingForm.getImage());
                building.setDescription(createBuildingForm.getDescription());
                building.setUtilities(createBuildingForm.getUtilities());
                building.setRules(createBuildingForm.getRules());
                building.setRulesImage(createBuildingForm.getRulesImage());
                Account account = accountService.getAccountById(Long.parseLong(createBuildingForm.getAccountId()));
                List<Building> buildingList =  account.getBuildings().stream().collect(Collectors.toList());
                buildingList.add(building);
                account.setBuildings(buildingList);
                accountService.updateAccount(account);
                addStatus = true;
            }else {
                return addStatus;
            }
        }catch (Exception e){
            throw new RuntimeException("Error Building Manage Repository "+ e.getMessage());
        }
        return addStatus;
    }

    @Override
    public Boolean updateBuilding(UpdateBuildingForm updateBuildingForm) {
        boolean updateStatus = false;
        try{
            Building building = null;
            building = buildingManageRepository.getBuildingById(Long.parseLong(updateBuildingForm.getId()));
            if(building != null){
                if(StringUtils.isNotEmpty(updateBuildingForm.getName())){
                    building.setName(updateBuildingForm.getName());
                }
                if(StringUtils.isNotEmpty(updateBuildingForm.getCity())
                        && StringUtils.isNotEmpty(updateBuildingForm.getDistrict())
                        && StringUtils.isNotEmpty(updateBuildingForm.getWards())){
                    building.setAddress(updateBuildingForm.getAddress()+ " " + updateBuildingForm.getWards()+ " "  + updateBuildingForm.getDistrict()+ " "  + updateBuildingForm.getCity());
                }
                    building.setImage(updateBuildingForm.getImage());
                    building.setDescription(updateBuildingForm.getDescription());
                    building.setUtilities(updateBuildingForm.getUtilities());
                    building.setRules(updateBuildingForm.getRules());
                    building.setRulesImage(updateBuildingForm.getRulesImage());
                buildingManageRepository.save(building);
                updateStatus = true;
            }else{
                return updateStatus;
            }
        }catch (Exception e){
            throw new RuntimeException("Error Building Manage Repository "+ e.getMessage());
        }
        return updateStatus;
    }

    @Override
    public Boolean deleteBuilding(Long id) {
        boolean deleteStatus = false;
        try {
            Building building = null;
            building = buildingManageRepository.getBuildingById(id);
            if(building != null){
                building.setDeleted(true);
                buildingManageRepository.save(building);
                deleteStatus = true;
            }else{
                return deleteStatus;
            }
        }catch (Exception e){
            throw new RuntimeException("Error Building Manage Repository "+ e.getMessage());
        }
        return deleteStatus;
    }


}
