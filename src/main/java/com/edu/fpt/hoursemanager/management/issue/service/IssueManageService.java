package com.edu.fpt.hoursemanager.management.issue.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.issue.model.request.CreateIssueRequest;
import com.edu.fpt.hoursemanager.management.issue.model.request.UpdateIssueRequest;
import org.springframework.http.ResponseEntity;

public interface IssueManageService {


    ResponseEntity<ResponseModels> getAllIssueByBuilding(Long id);
    ResponseEntity<ResponseModels> getAllIssueByStatus(Long id, String status);
    ResponseEntity<ResponseModels> getDetailIssue(Long id);
    ResponseEntity<ResponseModels> getAllIssueByRoom(Long id);
    ResponseEntity<ResponseModels> getAllIssueByAccount();

    ResponseEntity<ResponseModels> createIssue(CreateIssueRequest createIssueRequest);
    ResponseEntity<ResponseModels> updateIssue(UpdateIssueRequest updateIssueRequest);
    ResponseEntity<ResponseModels> deleteIssue(Long id);

}
