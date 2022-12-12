package com.edu.fpt.hoursemanager.management.account.service.impl;

import com.edu.fpt.hoursemanager.management.account.entity.Account;
import com.edu.fpt.hoursemanager.management.account.entity.Role;
import com.edu.fpt.hoursemanager.management.account.model.response.AccountResponse;
import com.edu.fpt.hoursemanager.management.account.repository.AccountRepository;
import com.edu.fpt.hoursemanager.management.account.repository.RoleRepository;
import com.edu.fpt.hoursemanager.management.account.service.AccountService;
import com.edu.fpt.hoursemanager.management.contact.entity.Contact;
import com.edu.fpt.hoursemanager.management.contact.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService , UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ContactRepository contactRepository;


    @Override
    public Account saveAccount(Account account) {
        log.info("Saving new user to database");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public List<Account> saveAccount(List<Account> accounts) {
        return accountRepository.saveAll(accounts.stream()
                .map(account ->  {account.setPassword(passwordEncoder.encode(account.getPassword()));
                    return account;})
                .collect(Collectors.toList()));
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(String username) {
        log.info("Fetching user {}",username);
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<AccountResponse> getAccounts() {
        log.info("Fetching all users");
        List<AccountResponse> accounts = accountRepository.getAllAccounts();
        return accounts;
    }

    @Override
    public Account getAccountById(Long id) {
        log.info("Fetching user {}",id);
        return accountRepository.findAccountById(id);
    }

    @Override
    public Account deleteAccount(String email) {
        Account acc = accountRepository.findByUsername(email);
        Contact contact = contactRepository.getAccountContactByEmail(email);
        contact.setDeleted(true);
        acc.setDeleted(true);
        contactRepository.save(contact);
        return accountRepository.save(acc);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(s);
        if(account == null ){
            log.error("User not found in the database");
            throw  new UsernameNotFoundException("user not found in the database");
        }else{
            log.info("User found in the database {}",s);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        });
        return new org.springframework.security.core.userdetails.User(account.getEmail(),
                account.getPassword(), authorities);
    }
}
