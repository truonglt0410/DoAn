package com.edu.fpt.hoursemanager.management.timesheet.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.WorkRequest;
import com.edu.fpt.hoursemanager.management.timesheet.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/work")
public class WorkController {
    @Autowired
    private WorkService workService;

    @PostMapping("/add")
    public ResponseEntity<ResponseModels> addWorkForTimeSheet(@RequestBody WorkRequest workRequest)  {
        return workService.addWork(workRequest);
    }

    @PostMapping("/get-all")
    public ResponseEntity<ResponseModels> lsAllWork()  {
        return workService.lsAllWork();
    }
}
