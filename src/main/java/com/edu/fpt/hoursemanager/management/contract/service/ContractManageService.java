package com.edu.fpt.hoursemanager.management.contract.service;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.contract.model.request.ContractRequest;
import com.edu.fpt.hoursemanager.management.contract.model.request.UpdateContractRequest;
import org.springframework.http.ResponseEntity;

public interface ContractManageService {

    ResponseEntity<ResponseModels> getAllContract();

    ResponseEntity<ResponseModels> getDetailContract(Long id);

    ResponseEntity<ResponseModels> createContract(ContractRequest contractRequest);

    ResponseEntity<ResponseModels> updateContract(UpdateContractRequest updateContractRequest);

    ResponseEntity<ResponseModels> deleteContract(Long id);

}
