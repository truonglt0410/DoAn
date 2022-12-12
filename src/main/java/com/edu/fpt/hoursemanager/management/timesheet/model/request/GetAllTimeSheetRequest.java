package com.edu.fpt.hoursemanager.management.timesheet.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTimeSheetRequest {
    private Date startDate;
    private Date endDate;
}
