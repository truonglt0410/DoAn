package com.edu.fpt.hoursemanager.management.roomservice.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.roomservice.model.request.ServiceRequest;
import org.springframework.http.ResponseEntity;

public interface ServiceService {
    ResponseEntity<ResponseModels> getAllService();

    ResponseEntity<ResponseModels> getAllServiceByIdRoom(Long id);

    ResponseEntity<ResponseModels> getAllServiceById(Long id);

    ResponseEntity<ResponseModels> deleteService(Long id);

    ResponseEntity<ResponseModels> createService(ServiceRequest service);

    ResponseEntity<ResponseModels> updateService(ServiceRequest service);

    ResponseEntity<ResponseModels> getAllServiceByBuildingId(Long id);

    ResponseEntity<ResponseModels> getAllServiceByAccount();

    ResponseEntity<ResponseModels> getAllServiceByIdBuilding(Long id);
}
