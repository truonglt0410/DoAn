package com.edu.fpt.hoursemanager.management.assets.service.Impl;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.assets.entity.Warehouse;
import com.edu.fpt.hoursemanager.management.assets.model.response.WarehouseResponse;
import com.edu.fpt.hoursemanager.management.assets.repository.WarehouseRepository;
import com.edu.fpt.hoursemanager.management.assets.service.WarehouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    @Override
    public ResponseEntity<ResponseModels> getAllWarehouse() {
        return ResponseModels.success(warehouseRepository.getAllWarehouse(), "success");
    }

    @Override
    public ResponseEntity<ResponseModels> createWarehouse(WarehouseResponse response) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(response.getNameWarehouse());
        return ResponseModels.success(warehouseRepository.save(warehouse), "add warehouse success!");
    }
}
