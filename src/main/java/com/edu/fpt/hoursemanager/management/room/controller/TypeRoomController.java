package com.edu.fpt.hoursemanager.management.room.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.exceptions.HouseManagerExceptions;
import com.edu.fpt.hoursemanager.management.room.model.request.CreateTypeRoomRequest;
import com.edu.fpt.hoursemanager.management.room.model.request.UpdateTypeRoomRequest;
import com.edu.fpt.hoursemanager.management.room.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/room/type")
public class TypeRoomController {
    @Autowired
    private TypeRoomService typeRoomService;

    @PostMapping("/update")
    public ResponseEntity<ResponseModels> updateTypeRoom(@RequestBody UpdateTypeRoomRequest updateTypeRoomRequest) throws HouseManagerExceptions {
        return typeRoomService.updateTypeRoom(updateTypeRoomRequest);
    }
    @GetMapping("/get-all")
    public ResponseEntity<ResponseModels> getAllTypeRoom(){
        return typeRoomService.getAllTypeRoom();
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseModels> createTypeRoom(@RequestBody CreateTypeRoomRequest createTypeRoomRequest) throws HouseManagerExceptions {
        return typeRoomService.createTypeRoom(createTypeRoomRequest);
    }
}
