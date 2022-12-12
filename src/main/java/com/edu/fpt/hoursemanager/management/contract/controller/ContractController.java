package com.edu.fpt.hoursemanager.management.contract.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.contract.model.request.ContractRequest;
import com.edu.fpt.hoursemanager.management.contract.model.request.UpdateContractRequest;
import com.edu.fpt.hoursemanager.management.contract.service.ContractManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    ContractManageService contractManageService;

    @GetMapping(value = "/get-all")
    public ResponseEntity<ResponseModels> getAllContract(){
        return contractManageService.getAllContract();
    }

    @GetMapping(value = "/get-detail")
    public ResponseEntity<ResponseModels> getDetailContract(@RequestParam Long id){
        return contractManageService.getDetailContract(id);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModels> createConrtact(@RequestBody ContractRequest registerRequest){
        return contractManageService.createContract(registerRequest);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ResponseModels> updateContract(@RequestBody UpdateContractRequest updateContractRequest){
        return contractManageService.updateContract(updateContractRequest);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<ResponseModels> deleteContract(@RequestParam Long id){
        return contractManageService.deleteContract(id);
    }
}
