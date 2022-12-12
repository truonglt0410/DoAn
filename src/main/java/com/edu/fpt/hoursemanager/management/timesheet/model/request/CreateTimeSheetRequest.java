package com.edu.fpt.hoursemanager.management.timesheet.model.request;

import com.edu.fpt.hoursemanager.management.timesheet.entity.Work;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTimeSheetRequest {
    private Date time;
    private boolean checkDone;
    private String note;
    private Long idRoom;
    private Long idAccount;
    private Long idWork;
}
