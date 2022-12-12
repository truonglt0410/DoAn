package com.edu.fpt.hoursemanager.management.account.service.impl;

import com.edu.fpt.hoursemanager.management.account.entity.Role;
import com.edu.fpt.hoursemanager.management.account.repository.RoleRepository;
import com.edu.fpt.hoursemanager.management.account.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> saveRole(List<Role> roles) {
        return roleRepository.saveAll(roles);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }
}
