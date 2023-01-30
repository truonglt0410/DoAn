package com.edu.fpt.hoursemanager.management.building.repository;

import com.edu.fpt.hoursemanager.management.building.entity.Utilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilitiesManageRepository extends JpaRepository<Utilities, Long> {

    @Query("select u from Utilities u where u.deleted = false")
    List<Utilities> getAllUtilities();

    @Query("select u from Utilities u where u.name = :name and  u.deleted = false")
    Utilities checkExist(@Param("name") String name);
}
