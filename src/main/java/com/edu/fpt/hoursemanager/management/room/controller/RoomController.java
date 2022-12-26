package com.edu.fpt.hoursemanager.management.room.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.room.model.request.AddAssetToRoomRequest;
import com.edu.fpt.hoursemanager.management.room.model.request.CreateRoomRequest;
import com.edu.fpt.hoursemanager.management.room.model.request.UpdateRoomRequest;
import com.edu.fpt.hoursemanager.management.room.service.RoomService;
import com.edu.fpt.hoursemanager.management.room.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/room")
public class RoomController {
    @Autowired
    TypeRoomService typeRoomService;

    @Autowired
    RoomService roomService;

    @PostMapping("/add")
    public ResponseEntity<ResponseModels> addRoom(@RequestBody CreateRoomRequest createRoomRequest) {
        return roomService.addRoom(createRoomRequest);
    }

    @PostMapping("/add-asset")
    public ResponseEntity<ResponseModels> addAssetToRoom(@RequestBody AddAssetToRoomRequest addAssetToRoomRequest) {
        return roomService.addAssetToRoom(addAssetToRoomRequest);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseModels> getAllRoom() {
        return roomService.getAllRoom();
    }

    @GetMapping("/get-all-by-id")
    public ResponseEntity<ResponseModels> viewRoomById(@RequestParam Long id) {
        return roomService.viewRoomById(id);
    }

    @GetMapping("/get-all-by-name-and-building")
    public ResponseEntity<ResponseModels> searchRoomByName(@RequestParam String name, @RequestParam Long id) {
        return roomService.searchRoomByName(name, id);
    }

    @GetMapping("/get-all-in-building")
    public ResponseEntity<ResponseModels> viewAllRoomInBuilding(@RequestParam Long id) {
        return roomService.getAllRoomInBuilding(id);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseModels> updateRoom(@RequestBody UpdateRoomRequest updateRoomRequest) {
        return roomService.updateRoom(updateRoomRequest);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModels> deleteRoom(@RequestParam Long id) {
        return roomService.deleteRoom(id);
    }

    @GetMapping("/get-detail-by-id")
    public ResponseEntity<ResponseModels> viewDetailRoomById(@RequestParam Long id) {
        return roomService.viewDetailRoomById(id);
    }

}
