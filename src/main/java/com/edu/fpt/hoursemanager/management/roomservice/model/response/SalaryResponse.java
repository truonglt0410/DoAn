package com.edu.fpt.hoursemanager.management.roomservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryResponse {
    private Long id;
    private String name;
    private Long roomId;
    private String roomName;
    private Double roomRate;
    private Boolean status;
    private Date fromDate;
    private Date toDate;
    private List<ServiceResponse> serviceList;
    private Double sumPrice;
}
