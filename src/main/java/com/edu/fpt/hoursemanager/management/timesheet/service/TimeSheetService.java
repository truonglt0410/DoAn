package com.edu.fpt.hoursemanager.management.timesheet.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.CreateTimeSheetRequest;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.EditTimeSheetRequest;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.GetAllTimeSheetRequest;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface TimeSheetService {
    ResponseEntity<ResponseModels> getAllTimeSheet(GetAllTimeSheetRequest request);

    ResponseEntity<ResponseModels> getAllTimeSheetByAccountLogin(GetAllTimeSheetRequest request);

    ResponseEntity<ResponseModels> deleteTimeSheet(Long id);

    ResponseEntity<ResponseModels> createTimeSheetForContact(CreateTimeSheetRequest createTimeSheetRequest);

    ResponseEntity<ResponseModels> editTimeSheetForContact(EditTimeSheetRequest editTimeSheetRequest);
}
