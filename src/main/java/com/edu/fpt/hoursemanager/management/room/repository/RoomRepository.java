package com.edu.fpt.hoursemanager.management.room.repository;

import com.edu.fpt.hoursemanager.management.room.entity.Room;
import com.edu.fpt.hoursemanager.management.room.model.response.DetailRoomResponseFromQuery;
import com.edu.fpt.hoursemanager.management.room.model.response.GetRoomBuildingResponse;
import com.edu.fpt.hoursemanager.management.room.model.response.GetRoomResponse;
import com.edu.fpt.hoursemanager.management.room.model.response.RoomResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select new com.edu.fpt.hoursemanager.management.room.model.response.GetRoomResponse(r.id,r.codeTypeRoom, r.name, r.roomImage) from Room r where r.id = :id and r.deleted = false")
    GetRoomResponse checkExist(@Param("id") String id);

    @Query("select new com.edu.fpt.hoursemanager.management.room.model.response.GetRoomBuildingResponse(r.id,tr.id, tr.capacity,r.name, r.roomImage,b.id, b.name, c.id, tr.price) " +
            "from Room r left join r.building b left join r.renterRooms rr left join rr.contact c inner join r.typeRooms tr where b.id = :id and r.deleted = false ")
    List<GetRoomBuildingResponse> listRoomInBuilding(@Param("id") Long id);

    @Query("select new com.edu.fpt.hoursemanager.management.room.model.response.GetRoomResponse(r.id,r.codeTypeRoom, r.name, r.roomImage) from Room r where r.id = :id and r.deleted = false ")
    GetRoomResponse viewRoomById(@Param("id") Long id);

    @Query("select new com.edu.fpt.hoursemanager.management.room.model.response.DetailRoomResponseFromQuery(r.id, r.name , tr.name , tr.capacity, tr.deposit, tr.price, tr.roomArea , b.name , a.name , a.color, a.model) From Room r left join r.typeRooms tr left join r.building b left join r.assets a where r.id = :id")
    List<DetailRoomResponseFromQuery> viewDetailRoomById(@Param("id") Long id);

    @Query("select new com.edu.fpt.hoursemanager.management.room.model.response.GetRoomResponse(r.id,r.codeTypeRoom, r.name, r.roomImage) from Room r where r.deleted = false")
    List<GetRoomResponse> getAllRoom();

    @Query("select new com.edu.fpt.hoursemanager.management.room.model.response.GetRoomResponse(r.id,r.codeTypeRoom, r.name, r.roomImage) from Room r where r.name LIKE %:name% and r.deleted = false ")
    List<GetRoomResponse> searchRoomByName(@Param("name") String name);

    @Query("select new com.edu.fpt.hoursemanager.management.room.model.response.RoomResponse(b.id,r.id,r.codeTypeRoom,r.name) from Room r inner join r.building b where r.name LIKE %:name% and b.id = :id and r.deleted = false")
    List<RoomResponse> searchRoomByNameAndBuilding(@Param("name") String name, @Param("id") Long id);

    @Query("select r from Room r where r.id = :id")
    Room getDetailRoom(@Param("id") Long id);
}
