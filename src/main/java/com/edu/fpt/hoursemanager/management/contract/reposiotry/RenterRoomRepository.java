package com.edu.fpt.hoursemanager.management.contract.reposiotry;

import com.edu.fpt.hoursemanager.management.contract.entity.RenterRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RenterRoomRepository extends JpaRepository<RenterRoom, Long> {

    @Query("select r from RenterRoom r where r.contract.id = :id")
    List<RenterRoom> getAllRenterRoomByContractId(@Param("id") Long id);

    @Modifying
    @Query("delete from RenterRoom r where r.contract.id = :id ")
    void removeRenterRoom(@Param("id") Long id);
}
