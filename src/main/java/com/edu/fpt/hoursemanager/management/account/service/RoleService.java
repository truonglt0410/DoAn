package com.edu.fpt.hoursemanager.management.account.service;

import com.edu.fpt.hoursemanager.management.account.entity.Role;

import java.util.List;

public interface RoleService {
    // Add a new role
    Role saveRole(Role role);

    List<Role> saveRole(List<Role> roles);
}
