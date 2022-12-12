package com.edu.fpt.hoursemanager.management.timesheet.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.CreateTimeSheetRequest;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.WorkRequest;
import org.springframework.http.ResponseEntity;

public interface WorkService {
    ResponseEntity<ResponseModels> addWork(WorkRequest workRequest);

    ResponseEntity<ResponseModels> lsAllWork();
}
