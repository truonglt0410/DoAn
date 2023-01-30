package com.edu.fpt.hoursemanager.management.room.service.impl;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.assets.entity.Assets;
import com.edu.fpt.hoursemanager.management.assets.repository.AssetRepository;
import com.edu.fpt.hoursemanager.management.building.entity.Building;
import com.edu.fpt.hoursemanager.management.room.entity.Room;
import com.edu.fpt.hoursemanager.management.room.entity.TypeRoom;
import com.edu.fpt.hoursemanager.management.room.model.request.AddAssetToRoomRequest;
import com.edu.fpt.hoursemanager.management.room.model.request.CreateRoomRequest;
import com.edu.fpt.hoursemanager.management.room.model.request.UpdateRoomRequest;
import com.edu.fpt.hoursemanager.management.room.model.response.*;
import com.edu.fpt.hoursemanager.management.room.repository.RoomRepository;
import com.edu.fpt.hoursemanager.management.room.repository.TypeRoomRepository;
import com.edu.fpt.hoursemanager.management.room.service.RoomService;
import com.edu.fpt.hoursemanager.management.room.service.TypeRoomService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@Slf4j
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private TypeRoomService typeRoomService;

    @Autowired
    private TypeRoomRepository typeRoomRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public ResponseEntity<ResponseModels> addRoom(CreateRoomRequest createRoomRequest) {
        try {
            Room room = new Room();
            TypeRoom typeRoom = typeRoomRepository.getTypeRoomById(createRoomRequest.getTypeRoomId());
            if (typeRoom != null) {
                typeRoom.setId(createRoomRequest.getTypeRoomId());
                room.setTypeRooms(typeRoom);
            }
            Building building = new Building();
            building.setId(createRoomRequest.getBuildingId());
            room.setName(createRoomRequest.getName());
            room.setRoomImage(createRoomRequest.getRoomImage());
            room.setCodeTypeRoom(createRoomRequest.getTypeRoom());
            room.setBuilding(building);
            roomRepository.save(room);
        } catch (Exception e) {
            return ResponseModels.error(e.getMessage());
        }
        return ResponseModels.success("add success");
    }

    @Override
    public ResponseEntity<ResponseModels> getAllRoomInBuilding(Long id) {
        Collection<GetRoomByBuildingAndContact> listRoomByBuilding = null;
        try {
            List<GetRoomBuildingResponse> getRoomBuildingResponses = roomRepository.listRoomInBuilding(id);
            Map<Long, GetRoomByBuildingAndContact> rooms = new HashMap<>();
            for (GetRoomBuildingResponse roomBuilding : getRoomBuildingResponses) {
                GetRoomByBuildingAndContact roomByBuildingAndContact = null;
                if (!rooms.containsKey(roomBuilding.getIdRoom())) {
                    roomByBuildingAndContact = new GetRoomByBuildingAndContact();
                    roomByBuildingAndContact.setIdRoom(roomBuilding.getIdRoom());
                    roomByBuildingAndContact.setNameRoom(roomBuilding.getNameRoom());
                    roomByBuildingAndContact.setIdBuilding(roomBuilding.getIdBuilding());
                    roomByBuildingAndContact.setBuildingName(roomBuilding.getBuildingName());
                    roomByBuildingAndContact.setCodeTypeRoom(roomBuilding.getCodeTypeRoom());
                    roomByBuildingAndContact.setImageRoom(roomBuilding.getRoomImage());
                    roomByBuildingAndContact.setCapacity(roomBuilding.getCapacity());
                    roomByBuildingAndContact.setPrice(roomBuilding.getPrice());
                    int status = 0;
                    if (roomBuilding.getContactId() != null) {
                        status++;
                    }
                    roomByBuildingAndContact.setStatus(status);
                    rooms.put(roomBuilding.getIdRoom(), roomByBuildingAndContact);
                } else {
                    roomByBuildingAndContact = rooms.get(roomBuilding.getIdRoom());
                    int status = 0;
                    if (roomBuilding.getContactId() != null) {
                        status = roomByBuildingAndContact.getStatus() + 1;
                    }
                    roomByBuildingAndContact.setStatus(status);
                    rooms.put(roomBuilding.getIdRoom(), roomByBuildingAndContact);
                }
            }
            listRoomByBuilding = rooms.values();
        } catch (Exception e) {
            return ResponseModels.error(e.getMessage());
        }
        return ResponseModels.success(listRoomByBuilding, "success");
    }

    @Override
    public ResponseEntity<ResponseModels> updateRoom(UpdateRoomRequest updateRoomRequest) {
        try {
            Room room = roomRepository.getDetailRoom(updateRoomRequest.getIdRoom());
            room.setName(updateRoomRequest.getNameRoom());
            room.setRoomImage(updateRoomRequest.getRoomImage());
            room.setCodeTypeRoom(updateRoomRequest.getCodeTypeRoom());
            if(updateRoomRequest.getIdTypeRoom()!= null){
                TypeRoom typeRoom = new TypeRoom();
                typeRoom.setId(updateRoomRequest.getIdTypeRoom());
                room.setTypeRooms(typeRoom);
            }else{
                room.setTypeRooms(null);
            }
            if(updateRoomRequest.getIdBuilding()!= null) {
                Building building = new Building();
                building.setId(updateRoomRequest.getIdBuilding());
                room.setBuilding(building);
            }else {
                room.setBuilding(null);
            }
            roomRepository.save(room);
        } catch (Exception e) {
            return ResponseModels.error(e.getMessage());
        }
        return ResponseModels.success("update success");
    }

    @Override
    public ResponseEntity<ResponseModels> viewRoomById(Long id) {
        return ResponseModels.success(roomRepository.viewRoomById(id));
    }

    @Override
    public ResponseEntity<ResponseModels> searchRoomByName(String name, Long id) {
        if (id == 0) {
            return ResponseModels.success(roomRepository.searchRoomByName(name));
        } else
            return ResponseModels.success(roomRepository.searchRoomByNameAndBuilding(name, id));
    }

    @Override
    public ResponseEntity<ResponseModels> getAllRoom() {
        return ResponseModels.success(roomRepository.getAllRoom());
    }

    @Override
    public ResponseEntity<ResponseModels> deleteRoom(Long id) {
        try {
            Room room = roomRepository.getById(id);
            room.setDeleted(true);
            roomRepository.save(room);
        } catch (Exception e) {
            return ResponseModels.success(e.getMessage());
        }
        return ResponseModels.success("Delete success");
    }

    @Override
    public ResponseEntity<ResponseModels> addAssetToRoom(AddAssetToRoomRequest addAssetToRoomRequest) {
        try {
            List<Assets> oldAssetsList = assetRepository.getAllAssetByRoom(addAssetToRoomRequest.getRoomId());
            if (oldAssetsList != null) {
                for (Assets assets : oldAssetsList) {
                    assets.setRoom(null);
                }
            }
            List<Assets> newAssetList = assetRepository.getAllAssetById(addAssetToRoomRequest.getAssetId());
            Room room;
            if (newAssetList != null) {
                for (Assets assets : newAssetList) {
                    room = new Room();
                    room.setId(addAssetToRoomRequest.getRoomId());
                    assets.setRoom(room);
                }
            }
            assetRepository.saveAll(newAssetList);
        } catch (Exception e) {
            return ResponseModels.success(e.getMessage());
        }
        return ResponseModels.success("Add Assets to Room success");
    }

    // @Override
    // public ResponseEntity<ResponseModels> getAllRoomDetail() {
    // List<RoomResponse> getRoomResponses = roomRepository.getAllRoomDetail();
    // List<GetRoomFinalResponse> getRoomFinalResponses = new ArrayList<>();
    // //logic
    // for(RoomResponse getRoomResponse : getRoomResponses){
    // // n?u t?n t?i building th� add th�m name room
    // int index = getRoomFinalResponses.indexOf(
    // new
    // GetRoomFinalResponse(getRoomResponse.getNameRoom(),getRoomResponse.getIdRoom(),getRoomResponse.getIdRoom(),getRoomResponse.getCodeTypeRoom(),getRoomResponse.getNameRoom(),null));
    // if(index != -1){
    // GetRoomFinalResponse getRoomFinalResponse =
    // new
    // GetRoomFinalResponse(getRoomResponse.getNameBuilding(),getRoomResponse.getIdBuilding(),getRoomResponse.getIdRoom(),getRoomResponse.getCodeTypeRoom(),getRoomResponse.getNameRoom(),
    // getRoomFinalResponses.get(index).getDataRoom());
    // getRoomFinalResponse.addDataRoom(getRoomResponse.getIdRoom(),getRoomResponse.getNameRoom(),getRoomResponse.getCodeTypeRoom());
    // getRoomFinalResponses.set(index,getRoomFinalResponse);
    // }else{
    // GetRoomFinalResponse getRoomFinalResponse =
    // new
    // GetRoomFinalResponse(getRoomResponse.getNameBuilding(),getRoomResponse.getIdBuilding(),getRoomResponse.getIdRoom(),
    // getRoomResponse.getCodeTypeRoom(),getRoomResponse.getNameRoom(),null);
    // getRoomFinalResponse.addDataRoom(getRoomResponse.getIdRoom(),getRoomResponse.getNameRoom(),getRoomResponse.getCodeTypeRoom());
    // getRoomFinalResponses.add(getRoomFinalResponse);
    // }
    // }
    // return ResponseModels.success(getRoomFinalResponses);
    // }

    public List<String> stringToList(String name) {
        List<String> list = new ArrayList<>();
        String[] listName = name.split(",");
        for (int i = 0; i < listName.length; i++) {
            if (!listName[i].isEmpty()) {
                list.add(listName[i]);
            }
        }
        return list;
    }

    @Override
    public ResponseEntity<ResponseModels> viewDetailRoomById(Long id) {
        DetailRoomResponseApi detailRoomResponseApi = new DetailRoomResponseApi();
        try {
            List<DetailRoomResponseFromQuery> detailRoomsQuery = roomRepository.viewDetailRoomById(id);
            List<DetailAssetsResponse> assets = new ArrayList<>();
            for (DetailRoomResponseFromQuery item : detailRoomsQuery) {
                if (detailRoomResponseApi.getId() == null) {
                    detailRoomResponseApi.setId(item.getId());
                    detailRoomResponseApi.setRoomName(item.getRoomName());
                    detailRoomResponseApi.setImageRoom(item.getImageRoom());
                    detailRoomResponseApi.setBuildingName(item.getBuildingName());
                    detailRoomResponseApi.setTypeRoomName(item.getTypeRoomName());
                    detailRoomResponseApi.setCapacity(item.getCapacity());
                    detailRoomResponseApi.setDeposit(item.getDeposit());
                    detailRoomResponseApi.setPrice(item.getPrice());
                    detailRoomResponseApi.setRoomArea(item.getRoomArea());
                }
                assets.add(new DetailAssetsResponse(item.getAssetsName(), item.getColor(), item.getModel()));
            }
            detailRoomResponseApi.setAssets(assets);
        } catch (Exception e) {
            return ResponseModels.error("Room Management Services "+ ": " + e.getMessage());
        }
        return ResponseModels.success(detailRoomResponseApi);
    }
}
