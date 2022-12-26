package com.edu.fpt.hoursemanager.management.contract.service.impl;

import com.edu.fpt.hoursemanager.common.entity.AccountLoginCommon;
import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.common.utils.Utils;
import com.edu.fpt.hoursemanager.management.contact.entity.Contact;
import com.edu.fpt.hoursemanager.management.contract.entity.Contract;
import com.edu.fpt.hoursemanager.management.contract.entity.RenterRoom;
import com.edu.fpt.hoursemanager.management.contract.model.request.ContractRequest;
import com.edu.fpt.hoursemanager.management.contract.model.request.UpdateContractRequest;
import com.edu.fpt.hoursemanager.management.contract.model.response.*;
import com.edu.fpt.hoursemanager.management.contract.reposiotry.ContractManageRepository;
import com.edu.fpt.hoursemanager.management.contract.reposiotry.RenterRoomRepository;
import com.edu.fpt.hoursemanager.management.contract.service.ContractManageService;
import com.edu.fpt.hoursemanager.management.room.entity.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ContractManageServiceImpl implements ContractManageService {
    @Autowired
    private ContractManageRepository contractManageRepository;
    @Autowired
    private RenterRoomRepository renterRoomRepository;

    private static final String CONTRACT_SERVICE = "Contract Management Services ";

    @Override
    public ResponseEntity<ResponseModels> getAllContract() {
        List<BasicContractResponse> list;
        try{
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(accountLoginCommon.getUserName() != null){
                list = contractManageRepository.getAllContract(accountLoginCommon.getUserName());
            }else {
                return ResponseModels.unauthorized();
            }
        }catch (Exception e){
            return ResponseModels.error(CONTRACT_SERVICE+ ": " + e.getMessage());
        }
        return ResponseModels.success(list);
    }

    @Override
    public ResponseEntity<ResponseModels> getDetailContract(Long id) {
        ContractDetailResponse contractDetailResponse = null;
        try{
            List<ContractResponse> listContract = contractManageRepository.getDetailContract(id);
            if(listContract != null){
                contractDetailResponse = new ContractDetailResponse();
                List<BasicRenterResponse> renters = new ArrayList<>();
                List<BasicRoomResponse> basicRoomResponses = new ArrayList<>();
                BasicRoomResponse room;
                BasicRenterResponse renter;
                for(ContractResponse contract : listContract){
                    if(contractDetailResponse.getId() == null){
                        contractDetailResponse.setId(contract.getId());
                        contractDetailResponse.setFromDate(Utils.covertDateToString(contract.getFromDate()));
                        contractDetailResponse.setToDate(Utils.covertDateToString(contract.getToDate()));
                        contractDetailResponse.setDeposit(contract.getDeposit());
                        contractDetailResponse.setRoomRate(contract.getRoomRate());
                        contractDetailResponse.setRoomPaymentCycle(contract.getRoomPaymentCycle());
                    }
                    room = new BasicRoomResponse();
                    room.setRoomId(contract.getRoomId());
                    room.setRoomName(contract.getRoomName());
                    room.setBuildingName(contract.getBuildingName());
                    if(!basicRoomResponses.contains(room)){
                        basicRoomResponses.add(room);
                    }
                    renter = new BasicRenterResponse();
                    renter.setId(contract.getRenterId());
                    renter.setName(contract.getRenterName());
                    renter.setDob(Utils.covertDateToString(contract.getDob()));
                    renter.setPhone(contract.getPhone());
                    renter.setImageAfter(contract.getImageAfter());
                    renter.setImageBefore(contract.getImageBefore());
                    renter.setNumberId(contract.getNumberId());
                    if (!renters.contains(renter)){
                        renters.add(renter);
                    }
                }
                contractDetailResponse.setRooms(basicRoomResponses);
                contractDetailResponse.setRenters(renters);
            }
        }catch (Exception e){
            return ResponseModels.error(CONTRACT_SERVICE+ ": " + e.getMessage());
        }
        return ResponseModels.success(contractDetailResponse);
    }

    @Override
    public ResponseEntity<ResponseModels> createContract(ContractRequest contractRequest) {
        try{
            Contract contract = null;
            for(Long id : contractRequest.getRooms()) contract = contractManageRepository.getContractByRoomId(id);
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(accountLoginCommon.getUserName() != null){
                if(contract == null){
                    contract = new Contract();
                    contract.setDeposit(contractRequest.getDeposit());
                    contract.setRoomRate(contractRequest.getRoomRate());
                    contract.setRoomPaymentCycle(contractRequest.getRoomPaymentCycle());
                    contract.setCreatedBy(accountLoginCommon.getUserName());
                    contract.setCreatedDate(LocalDate.now());
                    contract.setFromDate(Utils.covertStringToDate(contractRequest.getFromDate()));
                    contract.setToDate(Utils.covertStringToDate(contractRequest.getToDate()));

                    List<RenterRoom> renterRooms = getRenterRoom(contract, contractRequest.getRooms(),contractRequest.getContacts());
                    contract.setRenterRooms(renterRooms);
                    contractManageRepository.save(contract);
                }else {
                    return ResponseModels.error("Contract for this room is exist.");
                }
            }else {
                return ResponseModels.unauthorized();
            }
        }catch (Exception e){
            return ResponseModels.error(CONTRACT_SERVICE+ ": "+ e.getMessage());
        }
        return ResponseModels.created("Create Contract success.");
    }

    @Override
    public ResponseEntity<ResponseModels> updateContract(UpdateContractRequest updateContractRequest) {
        try{
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(accountLoginCommon.getUserName() != null){
                Contract contract = contractManageRepository.getByContractId(updateContractRequest.getId());
                contract.setFromDate(Utils.covertStringToDate(updateContractRequest.getFromDate()));
                contract.setToDate(Utils.covertStringToDate(updateContractRequest.getToDate()));
                contract.setDeposit(updateContractRequest.getDeposit());
                contract.setRoomRate(updateContractRequest.getRoomRate());
                contract.setRoomPaymentCycle(updateContractRequest.getRoomPaymentCycle());
                contract.setModifiedBy(accountLoginCommon.getUserName());
                contract.setModifiedDate(LocalDate.now());
                contractManageRepository.save(contract);

                renterRoomRepository.removeRenterRoom(contract.getId());

                List<RenterRoom> renterRooms = getRenterRoom(contract, updateContractRequest.getRooms(),updateContractRequest.getContacts());
                renterRoomRepository.saveAllAndFlush(renterRooms);
            }else {
                return ResponseModels.unauthorized();
            }
        }catch (Exception e){
            return ResponseModels.error(CONTRACT_SERVICE+ ": " + e.getMessage());
        }
        return ResponseModels.success("Update successful.");
    }

    @Override
    public ResponseEntity<ResponseModels> deleteContract(Long id) {
        try{
            AccountLoginCommon accountLoginCommon = new AccountLoginCommon();
            if(accountLoginCommon.getUserName() != null){
                Contract contract = contractManageRepository.getByContractId(id);
                contract.setDeleted(true);
                contractManageRepository.save(contract);
            }else {
                return ResponseModels.unauthorized();
            }
        }catch (Exception e){
            return ResponseModels.error(CONTRACT_SERVICE+ ": " + e.getMessage());
        }
        return ResponseModels.success("Delete Contract Success.");
    }

    private List<RenterRoom> getRenterRoom(Contract contract, List<Long> roomIds, List<Long> contactIds){
        List<RenterRoom> renterRooms = new ArrayList<>();
        RenterRoom renterRoom;
        Room room;
        Contact contact;
        for(Long contactId : contactIds){
            for(Long roomId : roomIds){
                room = new Room();
                room.setId(roomId);
                contact = new Contact();
                contact.setId(contactId);
                renterRoom = new RenterRoom();
                renterRoom.setRoom(room);
                renterRoom.setContract(contract);
                renterRoom.setContact(contact);
                renterRooms.add(renterRoom);
            }
        }
        return renterRooms;
    }
}
