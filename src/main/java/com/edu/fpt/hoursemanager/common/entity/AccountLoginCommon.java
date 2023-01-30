package com.edu.fpt.hoursemanager.common.entity;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountLoginCommon {
    private Authentication authentication;
    private String userName;
    private List<String> roleList;

    public AccountLoginCommon(){
        this.authentication = SecurityContextHolder.getContext().getAuthentication();
    }

    public String getUserName() {
        //************************************
        // Get username login information in the system
        //************************************
        if (!(this.authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = this.authentication.getName();
            return currentUserName;
        }
        return "";
    }

    public List<String> getRoleList() {
        //************************************
        // Get the login user's role information
        //************************************
        if (!(this.authentication instanceof AnonymousAuthenticationToken)) {
            return  this.authentication.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
