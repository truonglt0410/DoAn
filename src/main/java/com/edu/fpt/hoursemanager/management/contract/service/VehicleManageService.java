package com.edu.fpt.hoursemanager.management.contract.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.contract.model.request.AddVehicleRequest;
import com.edu.fpt.hoursemanager.management.contract.model.request.EditVehicleRequest;
import org.springframework.http.ResponseEntity;

public interface VehicleManageService {
    ResponseEntity<ResponseModels> getAllVehicleByContractId(Long id);

    ResponseEntity<ResponseModels> getAllVehicleByRoomId(Long id);

    ResponseEntity<ResponseModels> addVerhicle(AddVehicleRequest addVehicleRequest);

    ResponseEntity<ResponseModels> deleteVehicle(Long id);

    ResponseEntity<ResponseModels> editVehicle(EditVehicleRequest request);
}
