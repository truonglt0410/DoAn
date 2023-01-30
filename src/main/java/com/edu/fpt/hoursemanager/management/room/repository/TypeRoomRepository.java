package com.edu.fpt.hoursemanager.management.room.repository;

import com.edu.fpt.hoursemanager.management.room.entity.TypeRoom;
import com.edu.fpt.hoursemanager.management.room.model.response.GetTypeRoomResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeRoomRepository extends JpaRepository<TypeRoom, Long> {
    @Query("select new com.edu.fpt.hoursemanager.management.room.model.response.GetTypeRoomResponse(t.id,t.name,t.price,t.capacity,t.description,t.roomArea,t.deposit, t.idBuilding) from TypeRoom t where t.deleted = false ")
    List<GetTypeRoomResponse> getAllTypeRoom();

    @Query("select t from TypeRoom t where t.deleted = false and t.id = :id")
    TypeRoom getTypeRoomById(@Param("id") Long id);

    @Query("select t from TypeRoom t where t.idBuilding = :id and t.deleted = false ")
    List<TypeRoom> getTypeRoomByBuilding(@Param("id") Long id);

    @Query("select new com.edu.fpt.hoursemanager.management.room.model.response.GetTypeRoomResponse(t.id,t.name,t.price,t.capacity,t.description,t.roomArea,t.deposit, t.idBuilding) from TypeRoom t where t.idBuilding = :id and t.deleted = false ")
    List<GetTypeRoomResponse> getTypeRoomByBuilding1(@Param("id") Long id);
}
