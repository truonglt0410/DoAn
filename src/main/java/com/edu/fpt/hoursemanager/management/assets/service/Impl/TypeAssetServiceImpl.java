package com.edu.fpt.hoursemanager.management.assets.service.Impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.assets.entity.TypeAssets;
import com.edu.fpt.hoursemanager.management.assets.model.request.TypeAssetRequest;
import com.edu.fpt.hoursemanager.management.assets.repository.TypeAssetRepository;
import com.edu.fpt.hoursemanager.management.assets.service.TypeAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@Slf4j
public class TypeAssetServiceImpl implements TypeAssetService {

    @Autowired
    TypeAssetRepository typeAssetRepository;

    @Override
    public ResponseEntity<ResponseModels> getAllTypeAssets() {
        return ResponseModels.success(typeAssetRepository.getAllTypeAssets(), "success");
    }

    @Override
    public ResponseEntity<ResponseModels> createTypeAssets(TypeAssetRequest request) {
        AccountLoginCommon loginCommon = new AccountLoginCommon();
        TypeAssets typeAssets = new TypeAssets();
        typeAssets.setName(request.getName());
        typeAssets.setCode(request.getCode());
        typeAssets.setCreatedBy(loginCommon.getUserName());
        typeAssets.setCreatedDate(LocalDate.now());

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
}
