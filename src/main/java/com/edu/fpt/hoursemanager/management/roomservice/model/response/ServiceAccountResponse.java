package com.edu.fpt.hoursemanager.management.roomservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAccountResponse {
    private Long id;
    private String name;
    private Double price;
    private Long buildingId;
    private String email;
}
