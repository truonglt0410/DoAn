package com.edu.fpt.hoursemanager.management.issue.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.issue.model.request.CreateIssueRequest;
import com.edu.fpt.hoursemanager.management.issue.model.request.UpdateIssueRequest;
import com.edu.fpt.hoursemanager.management.issue.service.IssueManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/issue")
public class IssueController {
    @Autowired
    IssueManageService issueManageService;

    @GetMapping(value = "/get-all-by-building")
    public ResponseEntity<ResponseModels> getAllIssueByBuilding(@RequestParam Long id){
        return issueManageService.getAllIssueByBuilding(id);
    }

    @GetMapping(value = "/get-all-by-status")
    public ResponseEntity<ResponseModels> getAllIssueByStatus(@RequestParam("id") Long id,
                                                              @RequestParam("status")String status){
        return issueManageService.getAllIssueByStatus(id, status);
    }

    @GetMapping(value = "/get-all-by-room")
    public ResponseEntity<ResponseModels> getAllIssueByRoom(@RequestParam Long id){
        return issueManageService.getAllIssueByRoom(id);
    }

    @GetMapping(value = "/get-all-by-email")
    public ResponseEntity<ResponseModels> getAllIssueByEmail(){
        return issueManageService.getAllIssueByAccount();
    }

    @GetMapping(value = "/get-detail")
    public ResponseEntity<ResponseModels> getDetailIssue(@RequestParam Long id){
        return issueManageService.getDetailIssue(id);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModels> createIssue(@RequestBody CreateIssueRequest createIssueRequest){
        return issueManageService.createIssue(createIssueRequest);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ResponseModels> updateIssue(@RequestBody UpdateIssueRequest updateIssueRequest){
        return issueManageService.updateIssue(updateIssueRequest);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<ResponseModels> deleteIssue(@RequestParam Long id){
        return issueManageService.deleteIssue(id);
    }
}
