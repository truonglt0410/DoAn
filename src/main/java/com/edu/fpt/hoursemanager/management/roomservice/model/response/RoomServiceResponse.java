package com.edu.fpt.hoursemanager.management.roomservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomServiceResponse {
    private Long id;
    private String name;
    private Long roomId;
    private String roomName;
    private Double roomRate;
    private Long serviceId;
    private String serviceName;
    private Double servicePrice;
    private Boolean status;
    private Date fromDate;
    private Date toDate;
    private String typePayment;
    private Long idBuilding;
    private double amount;
    private String note;
    private double total;
}
