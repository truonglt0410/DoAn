package com.edu.fpt.hoursemanager.management.contact.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {
    private Long id;
    private String fullName;
    private String phone;
    private String dob;
    private Boolean gender;
    private String address;
    private String emailAccount;
    private String numberId;
    private String imageBefore;
    private String imageAfter;
    private Set<String> role;
}
