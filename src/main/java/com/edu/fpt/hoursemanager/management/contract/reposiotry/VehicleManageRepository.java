package com.edu.fpt.hoursemanager.management.contract.reposiotry;

import com.edu.fpt.hoursemanager.management.contract.entity.Vehicle;
import com.edu.fpt.hoursemanager.management.contract.model.response.VehicleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleManageRepository extends JpaRepository<Vehicle, Long> {
    @Query("select new com.edu.fpt.hoursemanager.management.contract.model.response.VehicleResponse(v.id, v.name, v.color, v.numberPlate, v.type, vc.id) " +
            "from Vehicle v inner join v.contract vc where  v.deleted = false and vc.id = :id")
    List<VehicleResponse> getAllVehicleByContractId(@Param("id") Long id);

    @Query("select v from Vehicle v where  v.deleted = false and v.id = :id")
    Vehicle getVehicleById(@Param("id") Long id);
}
