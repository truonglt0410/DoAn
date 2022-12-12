package com.edu.fpt.hoursemanager.management.assets.repository;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.assets.entity.Warehouse;
import com.edu.fpt.hoursemanager.management.assets.model.response.AssetResponse;
import com.edu.fpt.hoursemanager.management.assets.model.response.WarehouseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Query("select new com.edu.fpt.hoursemanager.management.assets.model.response.WarehouseResponse(w.id,w.name) from Warehouse w where w.deleted = false ")
    List<WarehouseResponse> getAllWarehouse();
}
