package com.edu.fpt.hoursemanager.management.authentication.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEmployeeRequest {
    private String email;
    private String password;
    private List<String> roles;

    private String fullName;
    private String phone;
    private String dob;
    private Boolean gender;
    private String address;
    private Boolean type;
}
