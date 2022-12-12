package com.edu.fpt.hoursemanager.management.roomservice.service.impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.building.entity.Building;
import com.edu.fpt.hoursemanager.management.building.repository.BuildingManageRepository;
import com.edu.fpt.hoursemanager.management.roomservice.model.request.ServiceRequest;
import com.edu.fpt.hoursemanager.management.roomservice.repository.ServiceRepository;
import com.edu.fpt.hoursemanager.management.roomservice.service.ServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@Slf4j
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    BuildingManageRepository buildingManageRepository;

    @Override
    public ResponseEntity<ResponseModels> getAllService() {
        return ResponseModels.success(serviceRepository.getAllService());
    }

    @Override
    public ResponseEntity<ResponseModels> getAllServiceByIdRoom(Long id) {
        return ResponseModels.success(serviceRepository.getAllServiceByRoom(id));
    }

    @Override
    public ResponseEntity<ResponseModels> getAllServiceById(Long id) {
        return ResponseModels.success(serviceRepository.getAllServiceById(id));
    }

    @Override
    public ResponseEntity<ResponseModels> deleteService(Long id) {
        AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
        com.edu.fpt.hoursemanager.management.roomservice.entity.Service service;
        try {
            service = serviceRepository.getServiceById(id);
            service.setModifiedBy(accountLoginCommon.getUserName());
            service.setModifiedDate(LocalDate.now());
            service.setDeleted(true);
            serviceRepository.save(service);
        }catch (Exception e){
            return ResponseModels.error();
        }
        return ResponseModels.success("Delete Service Success.");
    }

    @Override
    public ResponseEntity<ResponseModels> createService(ServiceRequest service) {
        try{
            com.edu.fpt.hoursemanager.management.roomservice.entity.Service serviceNew = new com.edu.fpt.hoursemanager.management.roomservice.entity.Service();
            serviceNew.setName(service.getNameService());
            serviceNew.setPrice(service.getPriceService());
            serviceNew.setTypePayment(service.getTypePayment());
            serviceNew.setElectric(service.isElectric());
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            serviceNew.setCreatedBy(accountLoginCommon.getUserName());
            serviceNew.setCreatedDate(LocalDate.now());
            if(service.getIdBuilding() != null){
                Building building = buildingManageRepository.getBuildingById(service.getIdBuilding());
                if(building!=null){
                    serviceNew.setBuilding(building);
                }
            }
            serviceRepository.save(serviceNew);
        }catch (Exception e){
            return ResponseModels.error("Add Services False : ", e.getMessage());
        }
        return ResponseModels.created("Add Services Success.");
    }

    @Override
    public ResponseEntity<ResponseModels> updateService(ServiceRequest request) {
        com.edu.fpt.hoursemanager.management.roomservice.entity.Service service;
        try {
            service = serviceRepository.getById(request.getIdService());
            service.setModifiedDate(LocalDate.now());
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            service.setModifiedBy(accountLoginCommon.getUserName());
            service.setName(request.getNameService());
            service.setPrice(request.getPriceService());
            service.setTypePayment(request.getTypePayment());
            service.setElectric(request.isElectric());
            if(request.getIdBuilding() != null){
                Building building = buildingManageRepository.getBuildingById(request.getIdBuilding());
                if(building!=null){
                    service.setBuilding(building);
                }
            }
            serviceRepository.save(service);
        }catch (Exception e){
            return ResponseModels.error();
        }
        return ResponseModels.success("Update Services Success.");
    }

    @Override
    public ResponseEntity<ResponseModels> getAllServiceByBuildingId(Long id) {
        return ResponseModels.success(serviceRepository.getAllServiceByBuilding(id), "Success.");
    }

    @Override
    public ResponseEntity<ResponseModels> getAllServiceByAccount() {
        AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
        if (accountLoginCommon != null){
            return ResponseModels.success(serviceRepository.getAllServiceByAccount(accountLoginCommon.getUserName()));
        }
        return ResponseModels.error("Not Service Found.");
    }

    @Override
    public ResponseEntity<ResponseModels> getAllServiceByIdBuilding(Long id) {
        return ResponseModels.success(serviceRepository.getAllServiceByIdBuilding(id));
    }
}
