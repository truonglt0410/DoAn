package com.edu.fpt.hoursemanager.management.contact.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {
    private String fullName;
    private String phone;
    private String dob;
    private String gender;
    private String address;
    private Boolean type;
}
