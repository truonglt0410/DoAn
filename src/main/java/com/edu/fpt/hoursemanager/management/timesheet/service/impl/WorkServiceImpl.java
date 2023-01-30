package com.edu.fpt.hoursemanager.management.timesheet.service.impl;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.timesheet.entity.TimeSheet;
import com.edu.fpt.hoursemanager.management.timesheet.entity.Work;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.WorkRequest;
import com.edu.fpt.hoursemanager.management.timesheet.model.response.TimeSheetResponse;
import com.edu.fpt.hoursemanager.management.timesheet.model.response.WorkResponse;
import com.edu.fpt.hoursemanager.management.timesheet.repository.TimeSheetRepository;
import com.edu.fpt.hoursemanager.management.timesheet.repository.WorkRepository;
import com.edu.fpt.hoursemanager.management.timesheet.service.WorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@Slf4j
public class WorkServiceImpl implements WorkService {
    @Autowired
    private TimeSheetRepository timeSheetRepository;

    @Autowired
    private WorkRepository workRepository;

    @Override
    public ResponseEntity<ResponseModels> addWork(WorkRequest workRequest) {
//        TimeSheet timeSheet = timeSheetRepository.getById(workRequest.getIdTimeSheet());
        Work work = new Work();
        work.setName(workRequest.getName());
        workRepository.save(work);
        return ResponseModels.success("Add success");
    }

    @Override
    public ResponseEntity<ResponseModels> lsAllWork() {
        return ResponseModels.success(workRepository.lsWorkResponse());
    }
}
