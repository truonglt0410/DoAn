package com.edu.fpt.hoursemanager.management.contact.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRoleResponse {
    private Long id;
    private String fullName;
    private String phone;
    private Date dob;
    private Boolean gender;
    private String address;
    private String emailAccount;
    private String role;
    private String numberId;
    private String imageBefore;
    private String imageAfter;
}
