package com.edu.fpt.hoursemanager.management.room.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.room.entity.TypeRoom;
import com.edu.fpt.hoursemanager.management.room.model.request.CreateTypeRoomRequest;
import com.edu.fpt.hoursemanager.management.room.model.request.UpdateTypeRoomRequest;
import org.springframework.http.ResponseEntity;

public interface TypeRoomService {
//    TypeRoom saveTypeRoom(TypeRoom typeRoom);

//    List<TypeRoom> saveTypeRooms(List<TypeRoom> typeRooms);

      TypeRoom getTypeRoomById(Long id);

      ResponseEntity<ResponseModels> updateTypeRoom(UpdateTypeRoomRequest updateTypeRoom);

      ResponseEntity<ResponseModels> getAllTypeRoom();

      ResponseEntity<ResponseModels> createTypeRoom(CreateTypeRoomRequest createTypeRoomRequest);
}
