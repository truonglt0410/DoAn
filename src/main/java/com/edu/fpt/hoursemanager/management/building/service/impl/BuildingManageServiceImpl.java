package com.edu.fpt.hoursemanager.management.building.service.impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.account.entity.Account;
import com.edu.fpt.hoursemanager.management.account.service.AccountService;
import com.edu.fpt.hoursemanager.management.assets.entity.TypeAssets;
import com.edu.fpt.hoursemanager.management.assets.repository.TypeAssetRepository;
import com.edu.fpt.hoursemanager.management.building.entity.Building;
import com.edu.fpt.hoursemanager.management.building.entity.Utilities;
import com.edu.fpt.hoursemanager.management.building.model.request.CreateBuildingRequest;
import com.edu.fpt.hoursemanager.management.building.model.request.UpdateBuildingRequest;
import com.edu.fpt.hoursemanager.management.building.model.response.BuildingDetailResponse;
import com.edu.fpt.hoursemanager.management.building.model.response.BuildingResponse;
import com.edu.fpt.hoursemanager.management.building.repository.BuildingManageRepository;
import com.edu.fpt.hoursemanager.management.building.service.BuildingManageService;
import com.edu.fpt.hoursemanager.management.building.service.UtilitiesManageService;
import com.edu.fpt.hoursemanager.management.room.entity.TypeRoom;
import com.edu.fpt.hoursemanager.management.room.repository.TypeRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class BuildingManageServiceImpl implements BuildingManageService {
    @Autowired
    private BuildingManageRepository buildingManageRepository;
    @Autowired
    private UtilitiesManageService utilitiesManageService;
    @Autowired
    private TypeAssetRepository typeAssetRepository;
    @Autowired
    private TypeRoomRepository typeRoomRepository;
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
    public ResponseEntity<ResponseModels> listAllBuildingByAccountId(){
        List<BuildingResponse> list = null;
        try {
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(!accountLoginCommon.getUserName().isEmpty() && accountLoginCommon.getUserName() != null){
                list = buildingManageRepository.getAllBuildingByAccountId(accountLoginCommon.getUserName());
            }
        } catch (Exception e) {
            return ResponseModels.error("Error Building Manage " + e.getMessage());
        }
        return ResponseModels.success(list);
    }

    @Override
    public ResponseEntity<ResponseModels> getDetailBuildingById(Long id){
        BuildingDetailResponse buildingResponse = new BuildingDetailResponse();
        try {
            Building building = buildingManageRepository.getBuildingById(id);
            if(building!= null){
                buildingResponse.setId(building.getId());
                buildingResponse.setName(building.getName());
                buildingResponse.setAddress(building.getAddress());
                buildingResponse.setDescription(building.getDescription());
                buildingResponse.setImage(building.getImage());
                buildingResponse.setRulesImage(building.getRulesImage());
                buildingResponse.setImageName(building.getImageName());
                buildingResponse.setLongitude(building.getLongitude());
                buildingResponse.setLatitude(building.getLatitude());
                List<String> rules = new ArrayList<>();
                List<String> utilities = new ArrayList<>();
                for(Utilities buildingUtilities : building.getUtilities()){
                    if(buildingUtilities.isType()){
                        rules.add(buildingUtilities.getName());
                    }else{
                        utilities.add(buildingUtilities.getName());
                    }
                }
                buildingResponse.setRules(rules);
                buildingResponse.setUtilities(utilities);
            }else {
                return ResponseModels.success("Building isn't Exist.");
            }
        } catch (Exception e) {
            return ResponseModels.error("Error Building Manage " + e.getMessage());
        }
        return ResponseModels.success(buildingResponse);
    }

    @Override
    public ResponseEntity<ResponseModels> addBuilding(CreateBuildingRequest createBuildingRequest) {
        try {
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(!accountLoginCommon.getUserName().isEmpty() && accountLoginCommon.getUserName() != null){
                String checkBuildingExist = buildingManageRepository.checkBuildingExist(createBuildingRequest.getName());
                if (checkBuildingExist == null) {
                    Building building = new Building();
                    building.setName(createBuildingRequest.getName());
                    building.setAddress(createBuildingRequest.getAddress());
                    building.setImage(createBuildingRequest.getImage());
                    building.setDescription(createBuildingRequest.getDescription());
                    building.setRulesImage(createBuildingRequest.getRulesImage());
                    building.setCreatedBy(accountLoginCommon.getUserName());
                    building.setModifiedBy(accountLoginCommon.getUserName());
                    building.setLongitude(createBuildingRequest.getLongitude());
                    building.setLatitude(createBuildingRequest.getLatitude());
                    building.setImageName(createBuildingRequest.getImageName());
                    // save rules and utilities to Building
                    utilitiesManageService.addUtilitiesToBuilding(building, createBuildingRequest.getRules(), createBuildingRequest.getUtilities());
                    Account account = accountService.getAccount(accountLoginCommon.getUserName());
                    Collection<Building> buildingList = account.getBuildings();
                    if(buildingList!= null){
                        buildingList.add(building);
                    }else {
                        buildingList = new ArrayList<>();
                        buildingList.add(building);
                    }
                    account.setBuildings(buildingList);
                    accountService.updateAccount(account);
                } else {
                    return ResponseModels.error("Building is Exist.");
                }
            }else {
                return ResponseModels.unauthorized();
            }
        } catch (Exception e) {
            return ResponseModels.error("Error Building Manage " + e.getMessage());
        }
        return ResponseModels.created("Add Building success.");
    }

    @Override
    public ResponseEntity<ResponseModels> updateBuilding(UpdateBuildingRequest updateBuildingRequest){
        try {
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            Building building = null;
            building = buildingManageRepository.getBuildingById(Long.parseLong(updateBuildingRequest.getId()));
            if (building != null) {
                if (StringUtils.isNotEmpty(updateBuildingRequest.getName())) {
                    building.setName(updateBuildingRequest.getName());
                }
                building.setAddress(updateBuildingRequest.getAddress());
                building.setImage(updateBuildingRequest.getImage());
                building.setDescription(updateBuildingRequest.getDescription());
                building.setRulesImage(updateBuildingRequest.getRulesImage());
                building.setModifiedBy(accountLoginCommon.getUserName());
                building.setLongitude(updateBuildingRequest.getLongitude());
                building.setLatitude(updateBuildingRequest.getLatitude());
                building.setImageName(updateBuildingRequest.getImageName());
                // save rules to Building
                utilitiesManageService.addUtilitiesToBuilding(building, updateBuildingRequest.getRules(), updateBuildingRequest.getUtilities());
                buildingManageRepository.save(building);
            } else {
                return ResponseModels.error("Not Building Found to Update.");
            }
        } catch (Exception e) {
            return ResponseModels.error("Error Building Manage " + e.getMessage());
        }
        return ResponseModels.success("Update Building success.");
    }

    @Override
    public ResponseEntity<ResponseModels> deleteBuilding(Long id){
        try {
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            Building building = null;
            building = buildingManageRepository.getBuildingById(id);
            if (building != null) {
                building.setDeleted(true);
                deleteTypeRoomAndTypeAssetWithBuilding(id);
                building.setModifiedBy(accountLoginCommon.getUserName());
                buildingManageRepository.save(building);
            } else {
                return ResponseModels.error("Not Building Found to Delete.");
            }
        } catch (Exception e) {
            return ResponseModels.error("Error Building Manage " + e.getMessage());
        }
        return ResponseModels.success("Delete Building success.");
    }


    private void deleteTypeRoomAndTypeAssetWithBuilding(Long idBuilding){
        List<TypeAssets> typeAssets = typeAssetRepository.getTypeAssetsByBuilding(idBuilding);
        if (typeAssets!= null){
            for(TypeAssets typeAsset : typeAssets ){
                typeAsset.setDeleted(true);
            }
            typeAssetRepository.saveAll(typeAssets);
        }

        List<TypeRoom> typeRooms = typeRoomRepository.getTypeRoomByBuilding(idBuilding);
        if(typeRooms != null){
            for(TypeRoom typeRoom : typeRooms){
                typeRoom.setDeleted(true);
            }
            typeRoomRepository.saveAll(typeRooms);
        }
    }

}
