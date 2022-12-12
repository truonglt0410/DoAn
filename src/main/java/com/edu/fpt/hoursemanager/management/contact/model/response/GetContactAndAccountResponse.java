package com.edu.fpt.hoursemanager.management.contact.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetContactAndAccountResponse {
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private Date dob;
    private Boolean gender;
    private String address;
    private Boolean type;
}
