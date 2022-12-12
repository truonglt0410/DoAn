package com.edu.fpt.hoursemanager.management.room.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomResponse {
    private Long idRoom;
    private String codeTypeRoom;
    private String nameRoom;
}
