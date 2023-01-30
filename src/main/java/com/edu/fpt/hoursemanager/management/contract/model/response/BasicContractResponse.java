package com.edu.fpt.hoursemanager.management.contract.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicContractResponse {
    private Long contractId;
    private String renterName;
    private String phone;
    private String buildingName;
    private String roomName;
}
