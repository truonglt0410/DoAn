package com.edu.fpt.hoursemanager.management.roomservice.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.roomservice.model.request.RoomServiceRequest;
import com.edu.fpt.hoursemanager.management.roomservice.service.RoomServiceManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/room-service")
public class RoomServiceController {
    @Autowired
    RoomServiceManage roomServiceManage;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseModels> getAllServiceOfRoomByMonth(@RequestParam String date) {
        return roomServiceManage.getAllServiceOfRoomByMonth(date);
    }

    @GetMapping("/get-detail")
    public ResponseEntity<ResponseModels> getDetailPayment(@RequestParam("roomId") Long id, @RequestParam("date") String date) {
        return roomServiceManage.getDetailPayment(id, date);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseModels> addRoomServices(@RequestBody RoomServiceRequest roomServiceRequest) {
        return roomServiceManage.addRoomServices(roomServiceRequest);
    }

    @PostMapping("/change-status")
    public ResponseEntity<ResponseModels> changeStatusServices(@RequestParam("roomId") Long id,
                                                               @RequestParam("date") String date,
                                                               @RequestParam("status") Boolean status) {
        return roomServiceManage.changeStatusServices(id,date, status);
    }

    @GetMapping("/get-electric-water-by-building-and-date")
    public ResponseEntity<ResponseModels> getElectricWaterByBuildingAndDate(@RequestParam Long idBuilding, @RequestParam String fromDate, String toDate) {
        return roomServiceManage.getRoomServiceElectricWaterByBuildingAndDate(idBuilding,fromDate,toDate);
    }
}
