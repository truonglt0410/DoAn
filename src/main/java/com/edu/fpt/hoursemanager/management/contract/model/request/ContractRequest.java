package com.edu.fpt.hoursemanager.management.contract.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractRequest {
    private String fromDate;
    private String toDate;
    private Double deposit;
    private Double roomRate;
    private int roomPaymentCycle;
    private List<Long> contacts;
    private List<Long> rooms;
}
