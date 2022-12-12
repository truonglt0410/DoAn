package com.edu.fpt.hoursemanager.management.room.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.room.model.request.AddAssetToRoomRequest;
import com.edu.fpt.hoursemanager.management.room.model.request.CreateRoomRequest;
import com.edu.fpt.hoursemanager.management.room.model.request.UpdateRoomRequest;
import org.springframework.http.ResponseEntity;

public interface RoomService {
    ResponseEntity<ResponseModels> addRoom(CreateRoomRequest createRoomRequest);

    ResponseEntity<ResponseModels> getAllRoomInBuilding(Long id);

    ResponseEntity<ResponseModels> updateRoom(UpdateRoomRequest updateRoomRequest);

    ResponseEntity<ResponseModels> viewRoomById(Long id);

    ResponseEntity<ResponseModels> searchRoomByName(String name, Long id);

    ResponseEntity<ResponseModels> getAllRoom();

    ResponseEntity<ResponseModels> deleteRoom(Long id);

    ResponseEntity<ResponseModels> addAssetToRoom(AddAssetToRoomRequest addAssetToRoomRequest);

}
