package com.edu.fpt.hoursemanager.management.issue.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetIssueByAccountResponse {
    private Long id;
    private String name;
    private LocalDate date;
    private String status;
    private String price;
    private String description;
    private Long roomId;
    private String roomName;
    private Long buildingId;
    private String buildingName;
    private String email;
    private String imageName;
}
