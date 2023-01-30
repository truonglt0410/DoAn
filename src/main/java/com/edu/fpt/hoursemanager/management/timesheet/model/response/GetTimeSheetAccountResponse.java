package com.edu.fpt.hoursemanager.management.timesheet.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTimeSheetAccountResponse {
    private Long accountId;
    private String accountName;
    private List<GetTimeSheetResponse> getTimeSheetResponses;

    public GetTimeSheetAccountResponse(Long accountId, String accountName) {
        this.accountId = accountId;
        this.accountName = accountName;
    }
}
