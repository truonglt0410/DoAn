package com.edu.fpt.hoursemanager.management.assets.service.Impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.assets.entity.Assets;
import com.edu.fpt.hoursemanager.management.assets.entity.TypeAssets;
import com.edu.fpt.hoursemanager.management.assets.model.request.AssetRequest;
import com.edu.fpt.hoursemanager.management.assets.repository.AssetRepository;
import com.edu.fpt.hoursemanager.management.assets.service.AssetService;
import com.edu.fpt.hoursemanager.management.room.entity.Room;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@Slf4j
public class AssetServiceImpl implements AssetService {
    @Autowired
    AssetRepository assetRepository;

    @Override
    public ResponseEntity<ResponseModels> getAllAssets() {
        return ResponseModels.success(assetRepository.getAllAssets(), "success");
    }

    @Override
    public ResponseEntity<ResponseModels> addAsset(AssetRequest assetRequest) {

        try {
            Assets assets = new Assets();
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();

            assets.setCreatedBy(accountLoginCommon.getUserName());
            assets.setCreatedDate(LocalDate.now());

            assets.setName(assetRequest.getName());
            assets.setColor(assetRequest.getColor());
            assets.setModel(assetRequest.getModel());
            assets.setStatus(assetRequest.getStatus());

            TypeAssets typeAssets = new TypeAssets();
            typeAssets.setId(assetRequest.getIdTypeAssets());
            assets.setTypeAssets(typeAssets);

            if(!String.valueOf(assetRequest.getIdRoom()).equals("") && assetRequest.getIdRoom()!=null){
                Room room = new Room();
                room.setId(assetRequest.getIdRoom());
                assets.setRoom(room);
            }

            assetRepository.save(assets);
        }catch (Exception e){
            return ResponseModels.error(e.getMessage());
        }
        return ResponseModels.success("success");
    }

    @Override
    public ResponseEntity<ResponseModels> updateAsset(AssetRequest assetRequest) {

        try {

            Assets assets = assetRepository.getById(assetRequest.getId());
            TypeAssets typeAssets = new TypeAssets();
//            Warehouse warehouse = new Warehouse();
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            assets.setModifiedBy(accountLoginCommon.getUserName());
            assets.setModifiedDate(LocalDate.now());

            assets.setName(assetRequest.getName());
            assets.setColor(assetRequest.getColor());
            assets.setModel(assetRequest.getModel());
            assets.setStatus(assetRequest.getStatus());

            typeAssets.setId(assetRequest.getIdTypeAssets());
            assets.setTypeAssets(typeAssets);

            if(StringUtils.isNotEmpty(String.valueOf(assetRequest.getIdRoom()))){
                Room room = new Room();
                room.setId(assetRequest.getIdRoom());
                assets.setRoom(room);
            }
//            warehouse.setId(assetRequest.getIdWarehouse());
//            assets.setWarehouse(warehouse);
            assetRepository.save(assets);
        }catch (Exception e){
            return ResponseModels.error(e.getMessage());
        }
        return ResponseModels.success("success");
    }

    @Override
    public ResponseEntity<ResponseModels> deleteAsset(Long id) {
        Assets assets = assetRepository.getById(id);
        AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
        try {
            assets.setModifiedDate(LocalDate.now());
            assets.setModifiedBy(accountLoginCommon.getUserName());

            assets.setDeleted(true);
            assetRepository.save(assets);
        }catch (Exception e){
            return ResponseModels.error(e.getMessage());
        }
        return ResponseModels.success("success");
    }

    @Override
    public ResponseEntity<ResponseModels> getAllAssetsNotRoom() {
        return ResponseModels.success(assetRepository.getAllAssetsNotRoom(), "success");
    }

    @Override
    public ResponseEntity<ResponseModels> getAssetByRoom(Long id) {
        return ResponseModels.success(assetRepository.getAssetByRoom(id), "success");
    }
}
