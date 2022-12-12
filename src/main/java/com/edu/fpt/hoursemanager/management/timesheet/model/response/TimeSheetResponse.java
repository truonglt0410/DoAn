package com.edu.fpt.hoursemanager.management.timesheet.model.response;

import com.edu.fpt.hoursemanager.management.room.model.response.GetRoomResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSheetResponse {
    private Date time;

    private boolean checkDone;

    private String note;

    private String nameWork;
}
