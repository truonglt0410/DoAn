package com.edu.fpt.hoursemanager.management.issue.service.impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.assets.entity.Assets;
import com.edu.fpt.hoursemanager.management.issue.entity.Issue;
import com.edu.fpt.hoursemanager.management.issue.model.request.CreateIssueRequest;
import com.edu.fpt.hoursemanager.management.issue.model.request.UpdateIssueRequest;
import com.edu.fpt.hoursemanager.management.issue.model.response.GetIssueByAccountResponse;
import com.edu.fpt.hoursemanager.management.issue.model.response.IssueResponse;
import com.edu.fpt.hoursemanager.management.issue.repository.IssueManageRepository;
import com.edu.fpt.hoursemanager.management.issue.service.IssueManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@Slf4j
public class IssueManageServiceImpl implements IssueManageService {

    @Autowired
    IssueManageRepository issueManageRepository;

    private static final String ISSUE_SERVICE = "Issue Service Error : ";

    @Override
    public ResponseEntity<ResponseModels> getAllIssueByBuilding(Long id) {
        List<IssueResponse> list;
        try{
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(accountLoginCommon.getUserName() != null){
                list = issueManageRepository.getAllIssueByBuildingId(id);
            }else {
                return ResponseModels.unauthorized();
            }
        }catch (Exception e){
            return ResponseModels.error(ISSUE_SERVICE+ ": " + e.getMessage());
        }
        return ResponseModels.success(list);
    }

    @Override
    public ResponseEntity<ResponseModels> getAllIssueByStatus(Long id, String status) {
        List<IssueResponse> list;
        try{
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(accountLoginCommon.getUserName() != null){
                list = issueManageRepository.getAllIssueByBuildingIdAndStatus(id, status);
            }else {
                return ResponseModels.unauthorized();
            }
        }catch (Exception e){
            return ResponseModels.error(ISSUE_SERVICE+ ": " + e.getMessage());
        }
        return ResponseModels.success(list);
    }

    @Override
    public ResponseEntity<ResponseModels> getDetailIssue(Long id) {
        IssueResponse issueResponse;
        try{
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(accountLoginCommon.getUserName() != null){
                issueResponse = issueManageRepository.getDetailIssue(id);
            }else {
                return ResponseModels.unauthorized();
            }
        }catch (Exception e){
            return ResponseModels.error(ISSUE_SERVICE+ ": " + e.getMessage());
        }
        return ResponseModels.success(issueResponse);
    }

    @Override
    public ResponseEntity<ResponseModels> getAllIssueByRoom(Long id) {
        List<IssueResponse> list;
        try{
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(accountLoginCommon.getUserName() != null){
                list = issueManageRepository.getAllIssueByRoom(id);
            }else {
                return ResponseModels.unauthorized();
            }
        }catch (Exception e){
            return ResponseModels.error(ISSUE_SERVICE+ ": " + e.getMessage());
        }
        return ResponseModels.success(list);
    }

    @Override
    public ResponseEntity<ResponseModels> getAllIssueByAccount() {
        List<GetIssueByAccountResponse> list;
        try{
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(accountLoginCommon.getUserName() != null){
                list = issueManageRepository.getAllIssueByAccount(accountLoginCommon.getUserName());
            }else {
                return ResponseModels.unauthorized();
            }
        }catch (Exception e){
            return ResponseModels.error(ISSUE_SERVICE+ ": " + e.getMessage());
        }
        return ResponseModels.success(list);
    }

    @Override
    public ResponseEntity<ResponseModels> createIssue(CreateIssueRequest createIssueRequest) {
        Issue issue = null;
        try{
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(accountLoginCommon.getUserName() != null){
                if(createIssueRequest != null){
                    issue = new Issue();
                    issue.setName(createIssueRequest.getName());
                    issue.setCreatedDate(LocalDate.now());
                    issue.setCreatedBy(accountLoginCommon.getUserName());
                    issue.setPrice(createIssueRequest.getPrice());
                    issue.setDescription(createIssueRequest.getDescription());
                    issue.setStatus(createIssueRequest.getStatus());
                    issue.setImageName(createIssueRequest.getImageName());
                    Assets assets = new Assets();
                    assets.setId(createIssueRequest.getAssetId());
                    issue.setAsset(assets);
                    issueManageRepository.save(issue);
                }else {
                    ResponseModels.error("Data is blank.");
                }
            }else {
                return ResponseModels.unauthorized();
            }
        }catch (Exception e){
            return ResponseModels.error(ISSUE_SERVICE+ ": " + e.getMessage());
        }
        return ResponseModels.created("Create Issue Success");
    }

    @Override
    public ResponseEntity<ResponseModels> updateIssue(UpdateIssueRequest updateIssueRequest) {
        Issue issue;
        try{
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(accountLoginCommon.getUserName() != null){
                issue = issueManageRepository.getIssueById(updateIssueRequest.getIssueId());
                if(issue != null){
                    issue.setName(updateIssueRequest.getName());
                    issue.setModifiedDate(LocalDate.now());
                    issue.setModifiedBy(accountLoginCommon.getUserName());
                    issue.setDescription(updateIssueRequest.getDescription());
                    issue.setPrice(updateIssueRequest.getPrice());
                    issue.setStatus(updateIssueRequest.getStatus());
                    issue.setImageName(updateIssueRequest.getImageName());
                    if(updateIssueRequest.getAssetId()!= null){
                        Assets assets = new Assets();
                        assets.setId(updateIssueRequest.getAssetId());
                        issue.setAsset(assets);
                    }
                    issueManageRepository.save(issue);
                }else {
                    return ResponseModels.error("Issue is not exist.");
                }
            }else {
                return ResponseModels.unauthorized();
            }
        }catch (Exception e){
            return ResponseModels.error(ISSUE_SERVICE+ ": " + e.getMessage());
        }
        return ResponseModels.success("Update Issue Success.");
    }

    @Override
    public ResponseEntity<ResponseModels> deleteIssue(Long id) {
        Issue issue;
        try{
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(accountLoginCommon.getUserName() != null){
                issue = issueManageRepository.getIssueById(id);
                if(issue != null){
                    issue.setDeleted(true);
                    issueManageRepository.save(issue);
                }else {
                    return ResponseModels.error("Issue is not exist.");
                }
            }else {
                return ResponseModels.unauthorized();
            }
        }catch (Exception e){
            return ResponseModels.error(ISSUE_SERVICE+ ": " + e.getMessage());
        }
        return ResponseModels.success("Delete Issue Success.");
    }
}
