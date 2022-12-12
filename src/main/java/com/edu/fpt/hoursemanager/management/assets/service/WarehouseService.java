package com.edu.fpt.hoursemanager.management.assets.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.assets.model.response.WarehouseResponse;
import org.springframework.http.ResponseEntity;

public interface WarehouseService {
    ResponseEntity<ResponseModels> getAllWarehouse();

    ResponseEntity<ResponseModels> createWarehouse(WarehouseResponse response);
}
