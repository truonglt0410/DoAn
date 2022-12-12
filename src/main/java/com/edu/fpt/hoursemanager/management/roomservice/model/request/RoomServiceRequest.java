package com.edu.fpt.hoursemanager.management.roomservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomServiceRequest {
    Long roomId;
    Long servicesId;
    String date;
    String name;
    private double amount;
    private Date fromDate;
    private String note;
}
