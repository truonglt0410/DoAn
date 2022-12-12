package com.edu.fpt.hoursemanager.management.account.model.request;

import com.edu.fpt.hoursemanager.management.account.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class AccountRequest {
    private String username;
    private String password;
    private List<Role> roles;

}
