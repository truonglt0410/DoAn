package com.edu.fpt.hoursemanager.management.contract.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.contract.model.request.AddVehicleRequest;
import com.edu.fpt.hoursemanager.management.contract.service.VehicleManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleManageService vehicleManageService;

    @GetMapping(value = "/get-all-by-contract")
    public ResponseEntity<ResponseModels> getAllContract(@RequestParam Long id){
        return vehicleManageService.getAllVehicleByContractId(id);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModels> createContact(@RequestBody AddVehicleRequest addVehicleRequest){
        return vehicleManageService.addVerhicle(addVehicleRequest);
    }


    @PostMapping(value = "/delete")
    public ResponseEntity<ResponseModels> deleteContact(@RequestParam Long id){
        return vehicleManageService.deleteVehicle(id);
    }
}
