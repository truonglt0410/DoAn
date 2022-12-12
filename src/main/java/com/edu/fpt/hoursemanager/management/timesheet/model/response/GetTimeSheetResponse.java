package com.edu.fpt.hoursemanager.management.timesheet.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTimeSheetResponse {
    private Long workId;
    private String workName;
    private Boolean checkDone;
    private String note;
}
