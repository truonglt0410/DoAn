package com.edu.fpt.hoursemanager.management.account.controller;

import com.edu.fpt.hoursemanager.common.models.ResponseModels;
import com.edu.fpt.hoursemanager.management.account.entity.Account;
import com.edu.fpt.hoursemanager.management.account.model.response.AccountResponse;
import com.edu.fpt.hoursemanager.management.account.service.AccountService;
import com.edu.fpt.hoursemanager.management.authentication.model.request.ChangePasswordRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseModels> getAllAccount(){
        List<AccountResponse> accountList = accountService.getAccounts();
        return ResponseModels.success(accountList);
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<ResponseModels> deleteAccount(@RequestParam String email){
        Account account = accountService.deleteAccount(email);
        return ResponseModels.success(account);
    }
}
