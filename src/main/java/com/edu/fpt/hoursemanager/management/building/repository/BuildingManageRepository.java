package com.edu.fpt.hoursemanager.management.building.repository;

import com.edu.fpt.hoursemanager.management.building.entity.Building;
import com.edu.fpt.hoursemanager.management.building.model.response.BuildingResponse;
import com.edu.fpt.hoursemanager.management.building.model.response.UtilitiesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingManageRepository extends JpaRepository<Building, Long> {

    @Query("select new com.edu.fpt.hoursemanager.management.building.model.response.BuildingResponse(b.id, b.name, b.address, b.image, b.longitude, b.latitude,ab.id) "+
            "from Building b inner join b.accounts ab where ab.email = :email and b.deleted = false")
    List<BuildingResponse> getAllBuildingByAccountId(@Param("email") String email);

    @Query("select new com.edu.fpt.hoursemanager.management.building.model.response.UtilitiesResponse(bu.name, bu.type) " +
            "from Building b inner join  b.utilities bu where b.id = :id and bu.deleted = false")
    List<UtilitiesResponse> getAllUtilitiesOfBuilding(@Param("id") Long id);

    @Query("select b.name from Building b where b.name = :name and b.deleted = false")
    String checkBuildingExist(@Param("name") String name);

    @Query("select b from Building b where b.id = :id and b.deleted = false")
    Building getBuildingById(@Param("id") long id);

}
