package com.edu.fpt.hoursemanager.management.timesheet.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditTimeSheetRequest {
    private Long idWork;
    private Long idTimeSheet;
    private String time;
    private boolean checkDone;
    private String note;
    private Long idRoom;
    private Long idAccount;
}
