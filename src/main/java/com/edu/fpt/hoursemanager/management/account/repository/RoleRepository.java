package com.edu.fpt.hoursemanager.management.account.repository;

import com.edu.fpt.hoursemanager.management.account.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByCode(String code);
    Role findByName(String code);
}
