package com.edu.fpt.hoursemanager.management.timesheet.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.CreateTimeSheetRequest;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.EditTimeSheetRequest;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.GetAllTimeSheetRequest;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.WorkRequest;
import com.edu.fpt.hoursemanager.management.timesheet.service.TimeSheetService;
import com.edu.fpt.hoursemanager.management.timesheet.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(path = "/time-sheet")
public class TimeSheetController {
    @Autowired
    TimeSheetService timeSheetService;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseModels> getAllTimeSheet(@RequestBody GetAllTimeSheetRequest request)  {
        return timeSheetService.getAllTimeSheet(request);
    }

    @GetMapping("/get-all-my-self")
    public ResponseEntity<ResponseModels> getAllTimeSheetByAccount(@RequestBody GetAllTimeSheetRequest request)  {
        return timeSheetService.getAllTimeSheetByAccountLogin(request);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseModels> createTimeSheet(@RequestBody CreateTimeSheetRequest createTimeSheetRequest)  {
        return timeSheetService.createTimeSheetForContact(createTimeSheetRequest);
    }

    @PostMapping("/edit")
    public ResponseEntity<ResponseModels> editTimeSheetForContact(@RequestBody EditTimeSheetRequest editTimeSheetRequest)  {
        return timeSheetService.editTimeSheetForContact(editTimeSheetRequest);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModels> deleteTimeSheetForContact(@RequestParam Long id)  {
        return timeSheetService.deleteTimeSheet(id);
    }
}
