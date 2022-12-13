package com.edu.fpt.hoursemanager.management.roomservice.service.impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.room.entity.Room;
import com.edu.fpt.hoursemanager.management.roomservice.entity.RoomService;
import com.edu.fpt.hoursemanager.management.roomservice.model.request.RoomServiceRequest;
import com.edu.fpt.hoursemanager.management.roomservice.model.response.*;
import com.edu.fpt.hoursemanager.management.roomservice.repository.RoomServiceRepository;
import com.edu.fpt.hoursemanager.management.roomservice.repository.ServiceRepository;
import com.edu.fpt.hoursemanager.management.roomservice.service.RoomServiceManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
@Slf4j
public class RoomServiceManageImpl implements RoomServiceManage {
    @Autowired
    private RoomServiceRepository roomServiceRepository;

    private static SimpleDateFormat SIMPLE_DATE = new SimpleDateFormat("dd-MM-yyyy");

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public ResponseEntity<ResponseModels> getAllServiceOfRoomByMonth(String date) {
        List<RoomServiceResponse> roomServiceResponses;
        Map<Long, List<BasicRoomServiceResponse>> map = new HashMap<>();
        BasicRoomServiceResponse serviceResponse;
        List<SalaryResponse> salaryResponses = new ArrayList<>();
        SalaryResponse roomService;
        try {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(SIMPLE_DATE.parse(date));
            Date fromDate;
            if(calendar.get(Calendar.DAY_OF_MONTH) >4){
                calendar.set(Calendar.DAY_OF_MONTH, 5);
                fromDate = calendar.getTime();
                calendar.add(Calendar.MONTH, 1);
            }else{
                calendar.set(Calendar.DAY_OF_MONTH, 5);
                calendar.add(Calendar.MONTH, -1);
                fromDate = calendar.getTime();
            }
            roomServiceResponses = roomServiceRepository.getAllRoomAndService(fromDate);
            for(RoomServiceResponse response : roomServiceResponses){
                if(!map.containsKey(response.getRoomId())){
                    roomService = new SalaryResponse();
                    roomService.setId(response.getId());
                    roomService.setName(response.getName());
                    roomService.setRoomId(response.getRoomId());
                    roomService.setRoomName(response.getRoomName());
                    roomService.setRoomRate(response.getRoomRate());
                    roomService.setStatus(response.getStatus());
                    roomService.setFromDate(response.getFromDate());
                    roomService.setToDate(response.getToDate());
                    serviceResponse = new BasicRoomServiceResponse(response.getServiceId(), response.getServiceName(), response.getServicePrice(), response.getAmount(), response.getTotal(), response.getTypePayment(), response.getIdBuilding());
                    map.put(response.getRoomId(), List.of(serviceResponse));
                    salaryResponses.add(roomService);
                }else {
                    List<BasicRoomServiceResponse> list = new ArrayList<>(map.get(response.getRoomId()));
                    serviceResponse = new BasicRoomServiceResponse(response.getServiceId(), response.getServiceName(), response.getServicePrice(), response.getAmount(), response.getTotal(), response.getTypePayment(), response.getIdBuilding());
                    list.add(serviceResponse);
                    map.put(response.getRoomId(), list);
                }
            }

            for(SalaryResponse salary : salaryResponses){
                Double sumSalary = 0.0;
                salary.setServiceList(map.get(salary.getRoomId()));
                for(BasicRoomServiceResponse service : map.get(salary.getRoomId())){
                    sumSalary += service.getSumPrice();
                }
                sumSalary += salary.getRoomRate() != null ?  salary.getRoomRate() : 0.0;
                salary.setSumPrice(sumSalary);
            }

        }catch (Exception e){
            return ResponseModels.error("Room Service Manage Error : " + e.getMessage());
        }
        return ResponseModels.success(salaryResponses);
    }

    @Override
    public ResponseEntity<ResponseModels> getDetailPayment(Long id, String date) {
        SalaryResponse salaryResponse = null;
        List<BasicRoomServiceResponse> serviceResponse = new ArrayList<>();
        Double sumPrice = 0.0;
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(SIMPLE_DATE.parse(date));
            Date fromDate;
            if(calendar.get(Calendar.DAY_OF_MONTH) >4){
                calendar.set(Calendar.DAY_OF_MONTH, 5);
                fromDate = calendar.getTime();
            }else{
                calendar.set(Calendar.DAY_OF_MONTH, 5);
                calendar.add(Calendar.MONTH, -1);
                fromDate = calendar.getTime();
            }
            List<RoomServiceResponse> roomServiceResponses = roomServiceRepository.getAllServiceByRoom(id,fromDate);
            if(roomServiceResponses!=null){
                for(RoomServiceResponse room : roomServiceResponses){
                    if(salaryResponse == null){
                        salaryResponse = new SalaryResponse();
                        salaryResponse.setId(id);
                        salaryResponse.setName(room.getName());
                        salaryResponse.setRoomId(room.getRoomId());
                        salaryResponse.setRoomName(room.getRoomName());
                        salaryResponse.setRoomRate(room.getRoomRate());
                        salaryResponse.setStatus(room.getStatus());
                        salaryResponse.setFromDate(room.getFromDate());
                        salaryResponse.setToDate(room.getToDate());
                        sumPrice += room.getRoomRate() != null ? room.getTotal() + room.getRoomRate() : room.getTotal() + 0.0;
                        salaryResponse.setSumPrice(sumPrice);
                        BasicRoomServiceResponse response = new BasicRoomServiceResponse(room.getServiceId(), room.getServiceName(), room.getServicePrice(), room.getAmount(), room.getTotal(), room.getTypePayment(), room.getIdBuilding());
                        serviceResponse.add(response);
                    }else {
                        BasicRoomServiceResponse response = new BasicRoomServiceResponse(room.getServiceId(), room.getServiceName(), room.getServicePrice(), room.getAmount(), room.getTotal(), room.getTypePayment(), room.getIdBuilding());
                        serviceResponse.add(response);
                        sumPrice += room.getTotal();
                        salaryResponse.setSumPrice(sumPrice);
                    }
                }
                if(salaryResponse != null)
                salaryResponse.setServiceList(serviceResponse);
            }
        }catch (Exception e){
            return ResponseModels.error("Room Service Manage Error : " + e.getMessage());
        }
        return ResponseModels.success(salaryResponse);
    }

    @Override
    public ResponseEntity<ResponseModels> changeStatusServices(Long id, String date,Boolean status) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(SIMPLE_DATE.parse(date));
            Date fromDate;
            if(calendar.get(Calendar.DAY_OF_MONTH) >4){
                calendar.set(Calendar.DAY_OF_MONTH, 5);
                fromDate = calendar.getTime();
            }else{
                calendar.set(Calendar.DAY_OF_MONTH, 5);
                calendar.add(Calendar.MONTH, -1);
                fromDate = calendar.getTime();
            }
            List<RoomService> roomServices = roomServiceRepository.getAllRoomServiceByRoomAndDate(id, fromDate);
            if(!roomServices.isEmpty()){
                for(RoomService roomService : roomServices){
                    roomService.setStatus(status);
                }
            }else {
                return ResponseModels.error("This Room Not Use Services in this Month.");
            }

        }catch (Exception e){
            return ResponseModels.error("Room Service Manage Error : " + e.getMessage());
        }
        return ResponseModels.success("Update Status Room Service Success.");
    }

    @Override
    public ResponseEntity<ResponseModels> addRoomServices(RoomServiceRequest roomServiceRequest) {
        try{
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(SIMPLE_DATE.parse(roomServiceRequest.getDate()));
            Date fromDate;
            Date toDate;
            if(calendar.get(Calendar.DAY_OF_MONTH) >4){
                calendar.set(Calendar.DAY_OF_MONTH, 5);
                fromDate = calendar.getTime();
                calendar.add(Calendar.MONTH, 1);
                toDate = calendar.getTime();
            }else{
                calendar.add(Calendar.MONTH, -1);
                fromDate = calendar.getTime();
                toDate = calendar.getTime();
            }
            if(roomServiceRepository.checkExistRoomService(roomServiceRequest.getRoomId(), roomServiceRequest.getServicesId(), fromDate) == null){
                List<CheckRoomStatusResponse> checkStatus = roomServiceRepository.getRoomStatus(roomServiceRequest.getRoomId(), fromDate, toDate);
                RoomService roomService = new RoomService();
                if(!checkStatus.isEmpty()){
                    Boolean status = checkStatus.get(0).getStatus();
                    roomService.setStatus(status);
                }else {
                    roomService.setStatus(false);
                }
                roomService.setName(roomServiceRequest.getName());
                roomService.setFromDate(fromDate);
                roomService.setToDate(toDate);
                com.edu.fpt.hoursemanager.management.roomservice.entity.Service service = new com.edu.fpt.hoursemanager.management.roomservice.entity.Service();
                service.setId(roomServiceRequest.getServicesId());
                roomService.setService(service);
                roomService.setAmount(roomServiceRequest.getAmount());
                com.edu.fpt.hoursemanager.management.roomservice.entity.Service service1 = serviceRepository.getServiceById(roomServiceRequest.getServicesId());
                roomService.setTotal(service1.getPrice() * roomServiceRequest.getAmount());
                roomService.setNote(roomServiceRequest.getNote());
                Room room = new Room();
                room.setId(roomServiceRequest.getRoomId());
                roomService.setRoom(room);
                roomService.setCreatedBy(accountLoginCommon.getUserName());
                roomServiceRepository.save(roomService);
            }else {
                return ResponseModels.success("Room Service is Exist.");
            }

        }catch (Exception e){
            return ResponseModels.error("Room Service Manage Error : " + e.getMessage());
        }
        return ResponseModels.created("Add Room Service Success.");
    }

    @Override
    public ResponseEntity<ResponseModels> getRoomServiceElectricWaterByBuildingAndDate(Long id, String fromDate, String toDate) {

        List<RoomServiceElectricWaterResponse> responseList = new ArrayList<>();

        try {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(SIMPLE_DATE.parse(date));
//            Date fromDate;
//            Date toDate;
//            if(calendar.get(Calendar.DAY_OF_MONTH) >4){
//                calendar.set(Calendar.DAY_OF_MONTH, 5);
//                fromDate = calendar.getTime();
//
//                calendar.add(Calendar.MONTH, 1);
//                calendar.set(Calendar.DAY_OF_MONTH, 4);
//                toDate=calendar.getTime();
//            }else{
//                calendar.set(Calendar.DAY_OF_MONTH, 4);
//                toDate = calendar.getTime();
//                calendar.set(Calendar.DAY_OF_MONTH, 5);
//                calendar.add(Calendar.MONTH, -1);
//                fromDate = calendar.getTime();
//            }
            Date fromDate1 =new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
            Date toDate1 =new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
            List<RoomServiceEWResponse> lsElectric = roomServiceRepository.getRoomServiceElectricByBuildingAndDate(id,fromDate1, toDate1);
            List<RoomServiceEWResponse> lsWater = roomServiceRepository.getRoomServiceWaterByBuildingAndDate(id,fromDate1, toDate1);
            for(RoomServiceEWResponse lsE : lsElectric){
                for(RoomServiceEWResponse lsW : lsWater){
                    if(Objects.equals(lsE.getIdRoom(), lsW.getIdRoom())){
                        RoomServiceElectricWaterResponse response = new RoomServiceElectricWaterResponse();
                        response.setAmountElectric("E"+String.valueOf(lsE.getAmount()));
                        response.setAmountWater("W"+String.valueOf(lsW.getAmount()));
                        response.setNameBuilding(lsE.getNameBuilding());
                        response.setNameRoom(lsE.getNameRoom());

                        response.setDateIncome(lsE.getDateIncome()+"");

                        responseList.add(response);
                    }else {
                        RoomServiceElectricWaterResponse responseE = new RoomServiceElectricWaterResponse();
                        responseE.setAmountElectric("E" + String.valueOf(lsE.getAmount()));
//                        responseE.setAmountWater(String.valueOf(lsW.getAmount()));
                        responseE.setNameBuilding(lsE.getNameBuilding());
                        responseE.setNameRoom(lsE.getNameRoom());

                        responseE.setDateIncome(lsE.getDateIncome()+"");
                        responseList.add(responseE);

                        RoomServiceElectricWaterResponse responseW = new RoomServiceElectricWaterResponse();
//                        responseW.setAmountElectric(String.valueOf(lsE.getAmount()));
                        responseW.setAmountWater("W"+String.valueOf(lsW.getAmount()));
                        responseW.setNameBuilding(lsE.getNameBuilding());
                        responseW.setNameRoom(lsE.getNameRoom());

                        responseE.setDateIncome(lsE.getDateIncome()+"");
                        responseList.add(responseW);
                    }
                }
            }
        }catch (Exception e){
            return ResponseModels.error("Room Service Manage Error : " + e.getMessage());
        }
        return ResponseModels.success(responseList);
    }

}
