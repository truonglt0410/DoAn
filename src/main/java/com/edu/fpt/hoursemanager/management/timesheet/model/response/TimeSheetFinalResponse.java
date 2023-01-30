package com.edu.fpt.hoursemanager.management.timesheet.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSheetFinalResponse {
    private Date time;

    private boolean checkDone;

    private String note;

    private List<WorkResponse> dataNameWork;

}
