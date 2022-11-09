package com.edu.fpt.hoursemanager.management.building.repository;

import com.edu.fpt.hoursemanager.management.building.entity.Building;
import com.edu.fpt.hoursemanager.management.building.model.response.BuildingDetailResponse;
import com.edu.fpt.hoursemanager.management.building.model.response.BuildingResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingManageRepository extends JpaRepository<Building, Long> {

    @Query("select new com.edu.fpt.hoursemanager.management.building.model.response.BuildingResponse(b.id, b.name, b.address, b.image, ab.id)"+
            "from Building b inner join b.accounts ab where ab.id = :id and b.deleted = false")
    List<BuildingResponse> getAllBuildingByAccountId(@Param("id") long id);

    @Query("select new com.edu.fpt.hoursemanager.management.building.model.response.BuildingDetailResponse(b.id, b.name, b.address, b.image, b.description, b.utilities, b.rules, b.rulesImage) " +
            "from Building b where b.id = :id and b.deleted = false")
    BuildingDetailResponse getDetailBuildingById(@Param("id") long id);

    @Query("select b.name from Building b where b.name = :name and b.deleted = false")
    String checkBuildingExist(@Param("name") String name);

    @Query("select b from Building b where b.id = :id and b.deleted = false")
    Building getBuildingById(@Param("id") long id);

}
