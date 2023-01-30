package com.edu.fpt.hoursemanager.management.roomservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomServiceRequest {
    Long roomId;
    Long servicesId;
    String date;
    String name;
    private double amount;
    private String fromDate;
    private String note;
}
