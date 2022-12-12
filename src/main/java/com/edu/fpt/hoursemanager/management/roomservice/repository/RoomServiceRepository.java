package com.edu.fpt.hoursemanager.management.roomservice.repository;

import com.edu.fpt.hoursemanager.management.roomservice.entity.RoomService;
import com.edu.fpt.hoursemanager.management.roomservice.model.response.CheckRoomStatusResponse;
import com.edu.fpt.hoursemanager.management.roomservice.model.response.RoomServiceEWResponse;
import com.edu.fpt.hoursemanager.management.roomservice.model.response.RoomServiceElectricWaterResponse;
import com.edu.fpt.hoursemanager.management.roomservice.model.response.RoomServiceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomServiceRepository extends JpaRepository<RoomService, Long> {
    @Query("select new com.edu.fpt.hoursemanager.management.roomservice.model.response.RoomServiceResponse(rs.id, rs.name, r.id, r.name, c.roomRate, s.id, s.name, s.price, rs.status, rs.fromDate, rs.toDate,s.typePayment,b.id,rs.amount,rs.note,rs.total) " +
            "from RoomService rs inner join rs.service s inner join rs.room r inner join r.renterRooms rr " +
            "inner join rr.contract c inner join r.building b where rs.fromDate = :fromDate ")
    List<RoomServiceResponse> getAllRoomAndService(@Param("fromDate") Date fromDate);

    @Query("select new com.edu.fpt.hoursemanager.management.roomservice.model.response.RoomServiceResponse(rs.id, rs.name, r.id, r.name, c.roomRate, s.id, s.name, s.price, rs.status, rs.fromDate, rs.toDate, s.typePayment, b.id,rs.amount,rs.note,rs.total) " +
            "from RoomService rs inner join rs.service s inner join rs.room r inner join r.renterRooms rr " +
            "inner join rr.contract c inner join r.building b where r.id = :id and rs.fromDate = :fromDate")
    List<RoomServiceResponse> getAllServiceByRoom(@Param("id") Long id, @Param("fromDate") Date fromDate);

    @Query("select new com.edu.fpt.hoursemanager.management.roomservice.model.response.CheckRoomStatusResponse(r.id, rs.status, rs.fromDate, rs.toDate)  " +
            "from RoomService rs inner join rs.room r where r.id = :id and rs.fromDate between :fromDate and :toDate")
    List<CheckRoomStatusResponse> getRoomStatus(@Param("id") Long id, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query("select rs from RoomService rs inner join rs.room r where r.id = :id and rs.fromDate = :fromDate ")
    List<RoomService> getAllRoomServiceByRoomAndDate(@Param("id") Long id, @Param("fromDate") Date fromDate);

    @Query("select rs from RoomService rs where rs.room.id = :roomId and rs.service.id = :serviceId and rs.fromDate = :date")
    RoomService checkExistRoomService(@Param("roomId") Long roomId,@Param("serviceId") Long serviceId, @Param("date") Date date);

    @Query("select new com.edu.fpt.hoursemanager.management.roomservice.model.response.RoomServiceEWResponse(r.id,rs.id, r.name,rs.amount,c.toDate,b.name) from RoomService rs inner join rs.room r inner join rs.service s inner join r.building b inner join r.renterRooms rr inner join rr.contract c where s.deleted = false and b.deleted = false and r.deleted = false and rs.deleted = false and b.id = :id and s.typePayment like 'AMOUNT' and s.isElectric = true  and rs.fromDate >= :fromDate and rs.toDate <= :toDate")
    List<RoomServiceEWResponse> getRoomServiceElectricByBuildingAndDate(@Param("id") Long id, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query("select new com.edu.fpt.hoursemanager.management.roomservice.model.response.RoomServiceEWResponse(r.id,rs.id,r.name,rs.amount,c.toDate,b.name) from RoomService rs inner join rs.room r inner join rs.service s inner join r.building b inner join r.renterRooms rr inner join rr.contract c where s.deleted = false and b.deleted = false and r.deleted = false and rs.deleted = false and b.id = :id and s.typePayment like 'AMOUNT' and s.isElectric = false  and rs.fromDate >= :fromDate and rs.toDate <= :toDate")
    List<RoomServiceEWResponse> getRoomServiceWaterByBuildingAndDate(@Param("id") Long id, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
