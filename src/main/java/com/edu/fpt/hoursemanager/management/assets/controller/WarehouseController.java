package com.edu.fpt.hoursemanager.management.assets.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.exceptions.HouseManagerExceptions;
import com.edu.fpt.hoursemanager.management.assets.model.response.WarehouseResponse;
import com.edu.fpt.hoursemanager.management.assets.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseModels> getAllWarehouses() throws HouseManagerExceptions {
        return warehouseService.getAllWarehouse();
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseModels> createWarehouses(@RequestBody WarehouseResponse response) throws HouseManagerExceptions {
        return warehouseService.createWarehouse(response);
    }
}
