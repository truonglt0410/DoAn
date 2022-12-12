package com.edu.fpt.hoursemanager.management.room.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomFinalResponse {

    @EqualsAndHashCode.Exclude
    private String nameBuilding;
    @EqualsAndHashCode.Exclude
    private Long idBuilding;
    @EqualsAndHashCode.Exclude
    private Long idRoom;
    @EqualsAndHashCode.Exclude
    private String codeTypeRoom;
    @EqualsAndHashCode.Exclude
    private String nameRoom;

    private List<GetRoomResponse> dataRoom;

    public List<GetRoomResponse> addDataRoom(Long idRoom, String nameRoom, String codeTypeRoom) {
        GetRoomResponse getRoomResponse = new GetRoomResponse();
        getRoomResponse.setIdRoom(idRoom);
        getRoomResponse.setNameRoom(nameRoom);
        getRoomResponse.setCodeTypeRoom(codeTypeRoom);
        dataRoom.add(getRoomResponse);
        return dataRoom;
    }

    public GetRoomFinalResponse(String nameBuilding, Long idBuilding, Long idRoom, String codeTypeRoom, String nameRoom) {
        this.nameBuilding = nameBuilding;
        this.idBuilding = idBuilding;
        this.idRoom = idRoom;
        this.codeTypeRoom = codeTypeRoom;
        this.nameRoom = nameRoom;
    }
}
