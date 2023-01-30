package com.edu.fpt.hoursemanager.management.roomservice.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.exceptions.HouseManagerExceptions;
import com.edu.fpt.hoursemanager.management.roomservice.model.request.ServiceRequest;
import com.edu.fpt.hoursemanager.management.roomservice.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/service")
public class ServiceController {
    @Autowired
    ServiceService serviceService;

    @GetMapping("/get-all-service-all-building")
    public ResponseEntity<ResponseModels> getAllServiceAllBuilding() {
        return serviceService.getAllServiceAllBuilding();
    }

    @GetMapping("/get-service-by-id")
    public ResponseEntity<ResponseModels> getServiceById(@RequestParam Long id) {
        return serviceService.getServiceById(id);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseModels> getAllService() {
        return serviceService.getAllService();
    }

    @GetMapping("/get-all-by-room-id")
    public ResponseEntity<ResponseModels> getAllServiceByRoomId(@RequestParam Long id) {
        return serviceService.getAllServiceByIdRoom(id);
    }

    @GetMapping("/get-all-by-id")
    public ResponseEntity<ResponseModels> getAllServiceById(@RequestParam Long id) {
        return serviceService.getAllServiceById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModels> deleteService(@RequestParam Long id)  {
        return serviceService.deleteService(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseModels> createService(@RequestBody ServiceRequest request)  {
        return serviceService.createService(request);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseModels> updateService(@RequestBody ServiceRequest request)  {
        return serviceService.updateService(request);
    }

    @GetMapping("/get-all-by-building-id")
    public ResponseEntity<ResponseModels> getAllServiceByBuildingId(@RequestParam Long id) {
        return serviceService.getAllServiceByBuildingId(id);
    }

    @GetMapping("/get-all-by-account")
    public ResponseEntity<ResponseModels> getAllServiceByAccount() {
        return serviceService.getAllServiceByAccount();
    }

    @GetMapping("/get-all-by-id-building")
    public ResponseEntity<ResponseModels> getAllServiceByIdBuilding(@RequestParam Long id) {
        return serviceService.getAllServiceByIdBuilding(id);
    }
}
