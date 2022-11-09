package com.edu.fpt.hoursemanager.management.account.service;

import com.edu.fpt.hoursemanager.management.account.entity.Account;
import com.edu.fpt.hoursemanager.management.account.model.response.AccountResponse;

import java.util.List;

public interface AccountService {
    // Add account
    Account saveAccount(Account account);

    List<Account> saveAccount(List<Account> accounts);

    Account updateAccount(Account account);

    // Get account by username
    Account getAccount(String username);
    //Get all accounts in system
    List<AccountResponse> getAccounts();

    Account getAccountById(Long id);
}
