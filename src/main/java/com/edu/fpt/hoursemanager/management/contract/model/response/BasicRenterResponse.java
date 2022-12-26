package com.edu.fpt.hoursemanager.management.contract.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicRenterResponse {
    private Long id;
    private String name;
    private String phone;
    private String numberId;
    private String imageBefore;
    private String imageAfter;
    private String dob;
}
