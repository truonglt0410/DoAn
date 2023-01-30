package com.edu.fpt.hoursemanager.management.timesheet.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTimeSheetRequest {
    private String startDate;
    private String endDate;
}
