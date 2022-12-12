package com.edu.fpt.hoursemanager.management.timesheet.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTimeSheetFinalResponse {
    private Date time;
    private List<GetTimeSheetAccountResponse> getTimeSheetAccountResponses;
}
