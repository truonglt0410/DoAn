package com.edu.fpt.hoursemanager.management.roomservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckRoomStatusResponse {
    private Long roomId;
    private Boolean status;
    private Date fromDate;
    private Date toDate;
}
