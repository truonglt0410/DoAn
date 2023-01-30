package com.edu.fpt.hoursemanager.management.room.service.impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.building.repository.BuildingManageRepository;
import com.edu.fpt.hoursemanager.management.room.entity.TypeRoom;
import com.edu.fpt.hoursemanager.management.room.model.request.CreateTypeRoomRequest;
import com.edu.fpt.hoursemanager.management.room.model.request.UpdateTypeRoomRequest;
import com.edu.fpt.hoursemanager.management.room.repository.TypeRoomRepository;
import com.edu.fpt.hoursemanager.management.room.service.TypeRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@Slf4j
public class TypeRoomServiceImpl implements TypeRoomService {

    @Autowired
    private TypeRoomRepository typeRoomRepository;


    @Autowired
    private BuildingManageRepository buildingManageRepository;

    @Override
    public TypeRoom getTypeRoomById(Long id){
        return typeRoomRepository.getById(id);
    }

    @Override
    public ResponseEntity<ResponseModels> updateTypeRoom(UpdateTypeRoomRequest updateTypeRoom) {
        try {
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            TypeRoom typeRoom = typeRoomRepository.getTypeRoomById(updateTypeRoom.getIdTypeRoom());
            typeRoom.setRoomArea(updateTypeRoom.getRoomArea());
            typeRoom.setDeposit(updateTypeRoom.getDeposit());
            typeRoom.setCapacity(updateTypeRoom.getCapacity());
            typeRoom.setPrice(updateTypeRoom.getPrice());
            typeRoom.setDescription(updateTypeRoom.getDescription());
            typeRoom.setName(updateTypeRoom.getName());
            typeRoom.setModifiedBy(accountLoginCommon.getUserName());
            typeRoom.setModifiedDate(LocalDate.now());
            if(buildingManageRepository.getBuildingById(updateTypeRoom.getIdBuilding())!=null){
                typeRoom.setIdBuilding(updateTypeRoom.getIdBuilding());
            }
            typeRoomRepository.save(typeRoom);
        }catch (Exception exception){
            return ResponseModels.error(exception.getMessage());
        }
        return ResponseModels.success("update success");
    }

    @Override
    public ResponseEntity<ResponseModels> getAllTypeRoom() {
        return ResponseModels.success(typeRoomRepository.getAllTypeRoom());
    }

    @Override
    public ResponseEntity<ResponseModels> getTypeRoomByBuilding(Long id) {
        return ResponseModels.success(typeRoomRepository.getTypeRoomByBuilding1(id));
    }

    @Override
    public ResponseEntity<ResponseModels> createTypeRoom(CreateTypeRoomRequest createTypeRoomRequest) {
        TypeRoom typeRoom = new TypeRoom();
        AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
        typeRoom.setRoomArea(createTypeRoomRequest.getRoomArea());
        typeRoom.setName(createTypeRoomRequest.getName());
        typeRoom.setDeposit(createTypeRoomRequest.getDeposit());
        typeRoom.setCapacity(createTypeRoomRequest.getCapacity());
        typeRoom.setDescription(createTypeRoomRequest.getDescription());
        typeRoom.setCreatedBy(accountLoginCommon.getUserName());
        typeRoom.setCreatedDate(LocalDate.now());
        typeRoom.setPrice(createTypeRoomRequest.getPrice());
        if(buildingManageRepository.getBuildingById(createTypeRoomRequest.getIdBuilding())!=null){
            typeRoom.setIdBuilding(createTypeRoomRequest.getIdBuilding());
        }
        return ResponseModels.success(typeRoomRepository.save(typeRoom));
    }

}
