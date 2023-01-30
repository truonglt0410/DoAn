package com.edu.fpt.hoursemanager.management.timesheet.service.impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.common.utils.Utils;
import com.edu.fpt.hoursemanager.management.contact.entity.Contact;
import com.edu.fpt.hoursemanager.management.contact.repository.ContactRepository;
import com.edu.fpt.hoursemanager.management.timesheet.entity.TimeSheet;
import com.edu.fpt.hoursemanager.management.timesheet.entity.Work;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.CreateTimeSheetRequest;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.EditTimeSheetRequest;
import com.edu.fpt.hoursemanager.management.timesheet.model.request.GetAllTimeSheetRequest;
import com.edu.fpt.hoursemanager.management.timesheet.model.response.GetTimeSheetAccountResponse;
import com.edu.fpt.hoursemanager.management.timesheet.model.response.GetTimeSheetFinalResponse;
import com.edu.fpt.hoursemanager.management.timesheet.model.response.GetTimeSheetFloorResponse;
import com.edu.fpt.hoursemanager.management.timesheet.model.response.GetTimeSheetResponse;
import com.edu.fpt.hoursemanager.management.timesheet.repository.TimeSheetRepository;
import com.edu.fpt.hoursemanager.management.timesheet.repository.WorkRepository;
import com.edu.fpt.hoursemanager.management.timesheet.service.TimeSheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@Slf4j
public class TimeSheetServiceImpl implements TimeSheetService {
    @Autowired
    TimeSheetRepository timeSheetRepository;

    @Autowired
    WorkRepository workRepository;

    @Autowired
    ContactRepository contactRepository;

    @Override
    public ResponseEntity<ResponseModels> getAllTimeSheet(GetAllTimeSheetRequest request) {
        Date startDate = Utils.covertStringToDate(request.getStartDate());
        Date endDate = Utils.covertStringToDate(request.getEndDate());

        List<GetTimeSheetFloorResponse> getTimeSheetFloorResponse = timeSheetRepository.lsGetAllFloor(startDate,endDate);

        List<GetTimeSheetFinalResponse> responseList = new ArrayList<>();

        List<GetTimeSheetResponse> works = new ArrayList<>();
        GetTimeSheetResponse timeSheetResponse = new GetTimeSheetResponse();

        List<GetTimeSheetAccountResponse> accounts = new ArrayList<>();
        GetTimeSheetAccountResponse timeSheetAccountResponse = new GetTimeSheetAccountResponse();

        List<GetTimeSheetFinalResponse> finals = new ArrayList<>();
        GetTimeSheetFinalResponse timeSheetFinalResponse = new GetTimeSheetFinalResponse();

        Map<GetTimeSheetAccountResponse, List<GetTimeSheetResponse>> mapAccount = new HashMap<>();

        Map<GetTimeSheetFinalResponse, List<GetTimeSheetAccountResponse>> mapFinal = new HashMap<>();

        for(GetTimeSheetFloorResponse response : getTimeSheetFloorResponse){
            timeSheetResponse = new GetTimeSheetResponse(response.getWorkId(), response.getWorkName(), response.getCheckDone(), response.getNote());

            timeSheetAccountResponse = new GetTimeSheetAccountResponse(response.getContactId(),response.getFullName(),List.of(timeSheetResponse));

            timeSheetFinalResponse = new GetTimeSheetFinalResponse(response.getTime(),List.of(timeSheetAccountResponse));


            if (!mapAccount.containsKey(timeSheetResponse)) {
                timeSheetResponse = new GetTimeSheetResponse(response.getWorkId(), response.getWorkName(), response.getCheckDone(), response.getNote());
                List<GetTimeSheetResponse> timeSheetResponses = List.of(timeSheetResponse);
                mapAccount.put(timeSheetAccountResponse,timeSheetResponses);
            }else {
                List<GetTimeSheetResponse> timeSheetResponses = new ArrayList<>(mapAccount.get(timeSheetAccountResponse));
                timeSheetResponse = new GetTimeSheetResponse(response.getWorkId(),response.getWorkName(),response.getCheckDone(),response.getNote());
                if(!timeSheetResponses.contains(timeSheetResponse)){
                    timeSheetResponses.add(timeSheetResponse);
                }
                mapAccount.put(timeSheetAccountResponse,timeSheetResponses);
            }

            if (!mapFinal.containsKey(timeSheetAccountResponse)) {
                timeSheetAccountResponse = new GetTimeSheetAccountResponse(response.getContactId(),response.getFullName(),List.of(timeSheetResponse));
                List<GetTimeSheetAccountResponse> timeSheetAccountResponses = List.of(timeSheetAccountResponse);
                mapFinal.put(timeSheetFinalResponse,timeSheetAccountResponses);
            }else {
                List<GetTimeSheetAccountResponse> timeSheetAccountResponses = new ArrayList<>(mapFinal.get(timeSheetFinalResponse));
                timeSheetAccountResponse = new GetTimeSheetAccountResponse(response.getContactId(),response.getFullName(),List.of(timeSheetResponse));
                if(!timeSheetAccountResponses.contains(timeSheetAccountResponse)){
                    timeSheetAccountResponses.add(timeSheetAccountResponse);
                }
                mapFinal.put(timeSheetFinalResponse,timeSheetAccountResponses);
            }
        }
        GetTimeSheetFinalResponse getTimeSheetFinalResponse;
        for(Map.Entry<GetTimeSheetFinalResponse,List<GetTimeSheetAccountResponse>> entry : mapFinal.entrySet()){
            getTimeSheetFinalResponse = new GetTimeSheetFinalResponse();
            getTimeSheetFinalResponse.setTime(entry.getKey().getTime());
            getTimeSheetFinalResponse.setGetTimeSheetAccountResponses(entry.getKey().getGetTimeSheetAccountResponses());
            if (entry.getValue().size() == 1) {
                for (GetTimeSheetAccountResponse value : entry.getValue()) {
                    if (value.getAccountName() == null || value.getAccountName().isEmpty()) {
                        getTimeSheetFinalResponse.setGetTimeSheetAccountResponses(new ArrayList<>());
                    } else {
                        getTimeSheetFinalResponse.setGetTimeSheetAccountResponses(entry.getValue());
                    }
                }
            } else {
                getTimeSheetFinalResponse.setGetTimeSheetAccountResponses(entry.getValue());
            }
            finals.add(getTimeSheetFinalResponse);
        }
        return ResponseModels.success(finals);
    }

    @Override
    public ResponseEntity<ResponseModels> getAllTimeSheetByAccountLogin(GetAllTimeSheetRequest request) {
        AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
        Date startDate = Utils.covertStringToDate(request.getStartDate());
        Date endDate = Utils.covertStringToDate(request.getEndDate());
        List<GetTimeSheetFloorResponse> getTimeSheetFloorResponse = timeSheetRepository.lsGetAllTimeByAccount(startDate,endDate,accountLoginCommon.getUserName());

        List<GetTimeSheetFinalResponse> responseList = new ArrayList<>();

        List<GetTimeSheetResponse> works = new ArrayList<>();
        GetTimeSheetResponse timeSheetResponse = new GetTimeSheetResponse();

        List<GetTimeSheetAccountResponse> accounts = new ArrayList<>();
        GetTimeSheetAccountResponse timeSheetAccountResponse = new GetTimeSheetAccountResponse();

        List<GetTimeSheetFinalResponse> finals = new ArrayList<>();
        GetTimeSheetFinalResponse timeSheetFinalResponse = new GetTimeSheetFinalResponse();

        Map<GetTimeSheetAccountResponse, List<GetTimeSheetResponse>> mapAccount = new HashMap<>();

        Map<GetTimeSheetFinalResponse, List<GetTimeSheetAccountResponse>> mapFinal = new HashMap<>();

        for(GetTimeSheetFloorResponse response : getTimeSheetFloorResponse){
            timeSheetResponse = new GetTimeSheetResponse(response.getWorkId(), response.getWorkName(), response.getCheckDone(), response.getNote());

            timeSheetAccountResponse = new GetTimeSheetAccountResponse(response.getContactId(),response.getFullName(),List.of(timeSheetResponse));

            timeSheetFinalResponse = new GetTimeSheetFinalResponse(response.getTime(),List.of(timeSheetAccountResponse));


            if (!mapAccount.containsKey(timeSheetResponse)) {
                timeSheetResponse = new GetTimeSheetResponse(response.getWorkId(), response.getWorkName(), response.getCheckDone(), response.getNote());
                List<GetTimeSheetResponse> timeSheetResponses = List.of(timeSheetResponse);
                mapAccount.put(timeSheetAccountResponse,timeSheetResponses);
            }else {
                List<GetTimeSheetResponse> timeSheetResponses = new ArrayList<>(mapAccount.get(timeSheetAccountResponse));
                timeSheetResponse = new GetTimeSheetResponse(response.getWorkId(),response.getWorkName(),response.getCheckDone(),response.getNote());
                if(!timeSheetResponses.contains(timeSheetResponse)){
                    timeSheetResponses.add(timeSheetResponse);
                }
                mapAccount.put(timeSheetAccountResponse,timeSheetResponses);
            }

            if (!mapFinal.containsKey(timeSheetAccountResponse)) {
                timeSheetAccountResponse = new GetTimeSheetAccountResponse(response.getContactId(),response.getFullName(),List.of(timeSheetResponse));
                List<GetTimeSheetAccountResponse> timeSheetAccountResponses = List.of(timeSheetAccountResponse);
                mapFinal.put(timeSheetFinalResponse,timeSheetAccountResponses);
            }else {
                List<GetTimeSheetAccountResponse> timeSheetAccountResponses = new ArrayList<>(mapFinal.get(timeSheetFinalResponse));
                timeSheetAccountResponse = new GetTimeSheetAccountResponse(response.getContactId(),response.getFullName(),List.of(timeSheetResponse));
                if(!timeSheetAccountResponses.contains(timeSheetAccountResponse)){
                    timeSheetAccountResponses.add(timeSheetAccountResponse);
                }
                mapFinal.put(timeSheetFinalResponse,timeSheetAccountResponses);
            }

        }
        GetTimeSheetFinalResponse getTimeSheetFinalResponse;
        for(Map.Entry<GetTimeSheetFinalResponse,List<GetTimeSheetAccountResponse>> entry : mapFinal.entrySet()){
            getTimeSheetFinalResponse = new GetTimeSheetFinalResponse();
            getTimeSheetFinalResponse.setTime(entry.getKey().getTime());
            getTimeSheetFinalResponse.setGetTimeSheetAccountResponses(entry.getKey().getGetTimeSheetAccountResponses());
            if (entry.getValue().size() == 1) {
                for (GetTimeSheetAccountResponse value : entry.getValue()) {
                    if (value.getAccountName() == null || value.getAccountName().isEmpty()) {
                        getTimeSheetFinalResponse.setGetTimeSheetAccountResponses(new ArrayList<>());
                    } else {
                        getTimeSheetFinalResponse.setGetTimeSheetAccountResponses(entry.getValue());
                    }
                }
            } else {
                getTimeSheetFinalResponse.setGetTimeSheetAccountResponses(entry.getValue());
            }
            finals.add(getTimeSheetFinalResponse);
        }
        return ResponseModels.success(finals);
    }

    @Override
    public ResponseEntity<ResponseModels> deleteTimeSheet(Long id) {
        TimeSheet timeSheet = timeSheetRepository.getById(id);
        timeSheet.setDeleted(true);
        return ResponseModels.success(timeSheetRepository.save(timeSheet));
    }

    @Override
    public ResponseEntity<ResponseModels> createTimeSheetForContact(CreateTimeSheetRequest createTimeSheetRequest) {
        AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
        Contact contact = contactRepository.getById(createTimeSheetRequest.getIdAccount());

        Work work = workRepository.getById(createTimeSheetRequest.getIdWork());
        TimeSheet timeSheet = new TimeSheet();
        timeSheet.setTime(Utils.covertStringToDate(createTimeSheetRequest.getTime()));
        timeSheet.setCheckDone(createTimeSheetRequest.isCheckDone());
        timeSheet.setNote(createTimeSheetRequest.getNote());
        timeSheet.setContact(contact);
        timeSheet.setWork(work);
        return ResponseModels.success(timeSheetRepository.save(timeSheet));
    }

    @Override
    public ResponseEntity<ResponseModels> editTimeSheetForContact(EditTimeSheetRequest editTimeSheetRequest) {
        Contact contact = contactRepository.getById(editTimeSheetRequest.getIdAccount());

        TimeSheet timeSheet = timeSheetRepository.getById(editTimeSheetRequest.getIdTimeSheet());
        timeSheet.setTime(Utils.covertStringToDate(editTimeSheetRequest.getTime()));
        timeSheet.setCheckDone(editTimeSheetRequest.isCheckDone());
        timeSheet.setNote(editTimeSheetRequest.getNote());
        timeSheet.setContact(contact);

        Work work = workRepository.getById(editTimeSheetRequest.getIdWork());

        timeSheet.setWork(work);

        return ResponseModels.success(timeSheetRepository.save(timeSheet));
    }
}
