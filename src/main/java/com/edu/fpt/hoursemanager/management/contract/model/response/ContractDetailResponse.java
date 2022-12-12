package com.edu.fpt.hoursemanager.management.contract.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDetailResponse {
    private Long id;
    private Date fromDate;
    private Date toDate;
    private Double deposit;
    private Double roomRate;
    private int roomPaymentCycle;
    private List<BasicRoomResponse> rooms;
    private List<BasicRenterResponse> renters;
}
