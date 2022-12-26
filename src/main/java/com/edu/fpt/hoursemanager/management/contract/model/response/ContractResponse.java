package com.edu.fpt.hoursemanager.management.contract.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractResponse {
    private Long id;
    private Date fromDate;
    private Date toDate;
    private Double deposit;
    private Double roomRate;
    private int roomPaymentCycle;
    private Long renterId;
    private String renterName;
    private String phone;
    private String numberId;
    private String imageBefore;
    private String imageAfter;
    private Date dob;
    private Long roomId;
    private String roomName;
    private String buildingName;
}
