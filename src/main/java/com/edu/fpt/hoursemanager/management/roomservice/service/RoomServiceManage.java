package com.edu.fpt.hoursemanager.management.roomservice.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.roomservice.model.request.RoomServiceRequest;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface RoomServiceManage {
    ResponseEntity<ResponseModels> getAllServiceOfRoomByMonth(String date) ;
    ResponseEntity<ResponseModels> getDetailPayment(Long id,String date) ;
    ResponseEntity<ResponseModels> changeStatusServices(Long id, String date, Boolean status) ;
    ResponseEntity<ResponseModels> addRoomServices(RoomServiceRequest roomServiceRequest) ;

    ResponseEntity<ResponseModels> getRoomServiceElectricWaterByBuildingAndDate(Long id, String fromDate, String toDate) ;
}
