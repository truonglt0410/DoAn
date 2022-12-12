package com.edu.fpt.hoursemanager.management.timesheet.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTimeSheetFloorResponse {
    private Date time;

    private Long contactId;
    private String fullName;

    private Long workId;
    private String workName;
    private Boolean checkDone;
    private String note;

}
