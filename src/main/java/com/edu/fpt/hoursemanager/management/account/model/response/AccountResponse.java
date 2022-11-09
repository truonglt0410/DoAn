package com.edu.fpt.hoursemanager.management.account.model.response;

import com.edu.fpt.hoursemanager.common.enums.ProviderAccount;
import com.edu.fpt.hoursemanager.management.account.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private Long id;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private ProviderAccount authProvider;

    private Long idRole;
    private String codeRole;
    private String nameRole;

}
