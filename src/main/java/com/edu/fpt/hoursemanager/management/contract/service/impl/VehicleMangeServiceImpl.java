package com.edu.fpt.hoursemanager.management.contract.service.impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.contract.entity.Contract;
import com.edu.fpt.hoursemanager.management.contract.entity.Vehicle;
import com.edu.fpt.hoursemanager.management.contract.model.request.AddVehicleRequest;
import com.edu.fpt.hoursemanager.management.contract.model.response.VehicleResponse;
import com.edu.fpt.hoursemanager.management.contract.reposiotry.VehicleManageRepository;
import com.edu.fpt.hoursemanager.management.contract.service.VehicleManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@Slf4j
public class VehicleMangeServiceImpl implements VehicleManageService {

    @Autowired
    private VehicleManageRepository vehicleManageRepository;

    private static final String VEHICLE_SERVICE = "Contract Management Services ";
    @Override
    public ResponseEntity<ResponseModels> getAllVehicleByContractId(Long id) {
        List<VehicleResponse> list;
        try {
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if (accountLoginCommon.getUserName() != null) {
                list = vehicleManageRepository.getAllVehicleByContractId(id);
            } else {
                return ResponseModels.unauthorized();
            }
        } catch (Exception e) {
            return ResponseModels.error(VEHICLE_SERVICE+": " + e.getMessage());
        }
        return ResponseModels.success(list);
    }

    @Override
    public ResponseEntity<ResponseModels> addVerhicle(AddVehicleRequest addVehicleRequest) {
        Vehicle vehicle = new Vehicle();
        try {
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if (accountLoginCommon.getUserName() != null) {
                if (addVehicleRequest != null) {
                    vehicle.setName(addVehicleRequest.getName());
                    vehicle.setColor(addVehicleRequest.getColor());
                    vehicle.setNumberPlate(addVehicleRequest.getNumberPlate());
                    vehicle.setType(addVehicleRequest.getType());
                    vehicle.setCreatedBy(accountLoginCommon.getUserName());
                    vehicle.setCreatedDate(LocalDate.now());
                    Contract contract = new Contract();
                    contract.setId(addVehicleRequest.getContractId());
                    vehicle.setContract(contract);
                    vehicleManageRepository.save(vehicle);
                }
            } else {
                return ResponseModels.unauthorized();
            }
        } catch (Exception e) {
            return ResponseModels.error(VEHICLE_SERVICE+": " + e.getMessage());
        }
        return ResponseModels.success("Add Vehicle Success.");
    }

    @Override
    public ResponseEntity<ResponseModels> deleteVehicle(Long id) {
        try {
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if (accountLoginCommon.getUserName() != null) {
                Vehicle vehicle = vehicleManageRepository.getVehicleById(id);
                vehicle.setDeleted(true);
            }
        } catch (Exception e) {
            return ResponseModels.error(VEHICLE_SERVICE+": " + e.getMessage());
        }
        return ResponseModels.success("Delete Vehicle Success.");
    }
}
