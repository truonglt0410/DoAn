package com.edu.fpt.hoursemanager.management.assets.service.Impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.assets.entity.TypeAssets;
import com.edu.fpt.hoursemanager.management.assets.model.request.TypeAssetRequest;
import com.edu.fpt.hoursemanager.management.assets.model.response.AssetResponse;
import com.edu.fpt.hoursemanager.management.assets.model.response.GetTypeAssetsResponseFromQuery;
import com.edu.fpt.hoursemanager.management.assets.model.response.TypeAssetsResponseApi;
import com.edu.fpt.hoursemanager.management.assets.repository.TypeAssetRepository;
import com.edu.fpt.hoursemanager.management.assets.service.TypeAssetService;
import com.edu.fpt.hoursemanager.management.building.repository.BuildingManageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class TypeAssetServiceImpl implements TypeAssetService {

    @Autowired
    TypeAssetRepository typeAssetRepository;

    @Autowired
    BuildingManageRepository buildingManageRepository;

    @Override
    public ResponseEntity<ResponseModels> getAllTypeAssets() {
        List<GetTypeAssetsResponseFromQuery> dataFromQuery = typeAssetRepository.getAllTypeAssets();
        List<TypeAssetsResponseApi> dataRes = new ArrayList<>();
        Long temp = 0L;
        int index =0;
        List<AssetResponse> lsAssets = new ArrayList<>();
        TypeAssetsResponseApi typeAsset = null;
        for (GetTypeAssetsResponseFromQuery item : dataFromQuery) {
            AssetResponse assets = new AssetResponse();
            
            if(item.getIdTypeAsset() != temp){
                index++;
                lsAssets = new ArrayList<>();
                typeAsset = new TypeAssetsResponseApi();
                temp = item.getIdTypeAsset();
                typeAsset.setIdTypeAsset(temp);
                typeAsset.setCodeTypeAsset(item.getCodeTypeAsset());
                typeAsset.setNameTypeAsset(item.getNameTypeAsset());
                assets.setIdAsset(item.getIdAsset());
                lsAssets.add(assets);
                typeAsset.setAssets(lsAssets);
                dataRes.add(typeAsset);
            }else{
                assets.setIdAsset(item.getIdAsset());
                lsAssets.add(assets);
                typeAsset.setAssets(lsAssets);
                dataRes.set(index-1, typeAsset);
            }
        }
        return ResponseModels.success(dataRes, "success");
    }

    @Override
    public ResponseEntity<ResponseModels> getTypeAssetsByBuilding(Long id) {
        return ResponseModels.success(typeAssetRepository.getTypeAssetsByBuilding1(id));
    }

    @Override
    public ResponseEntity<ResponseModels> createTypeAssets(TypeAssetRequest request) {
        AccountLoginCommon loginCommon = new AccountLoginCommon();
        TypeAssets typeAssets = new TypeAssets();
        typeAssets.setName(request.getName());
        typeAssets.setCode(request.getCode());
        typeAssets.setCreatedBy(loginCommon.getUserName());
        typeAssets.setCreatedDate(LocalDate.now());
        if(buildingManageRepository.getBuildingById(request.getIdBuilding())!=null){
            typeAssets.setIdBuilding(request.getIdBuilding());
        }
        return ResponseModels.success(typeAssetRepository.save(typeAssets), "success");
    }

    @Override
    public ResponseEntity<ResponseModels> deleteTypeAsset(Long id) {
        TypeAssets typeAssets;
        try{
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            typeAssets = typeAssetRepository.getTypeAssetById(id);
            if(typeAssets != null){
                typeAssets.setDeleted(true);
                typeAssets.setModifiedBy(accountLoginCommon.getUserName());
                typeAssets.setModifiedDate(LocalDate.now());
                typeAssetRepository.save(typeAssets);
            }else {
                return ResponseModels.error("Type Asset is not Exist.");
            }
        }catch (Exception e){
            return ResponseModels.error("Type Asset Service Error : "+e.getMessage());
        }
        return ResponseModels.success("Delete Type Asset success.");
    }

    @Override
    public ResponseEntity<ResponseModels> updateTypeAsset(TypeAssetRequest typeAssetRequest) {
        try {

            TypeAssets typeAssets = typeAssetRepository.getById(typeAssetRequest.getId());
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            typeAssets.setModifiedBy(accountLoginCommon.getUserName());
            typeAssets.setModifiedDate(LocalDate.now());

            typeAssets.setName(typeAssetRequest.getName());
            typeAssets.setCode(typeAssetRequest.getCode());
            if(buildingManageRepository.getBuildingById(typeAssetRequest.getIdBuilding())!=null){
                typeAssets.setIdBuilding(typeAssetRequest.getIdBuilding());
            }
            typeAssetRepository.save(typeAssets);
        }catch (Exception e){
            return ResponseModels.error(e.getMessage());
        }
        return ResponseModels.success("success");
    }
}
