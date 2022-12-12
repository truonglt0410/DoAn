package com.edu.fpt.hoursemanager.management.issue.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIssueRequest {
    private String name;
    private String status;
    private String price;
    private String description;
    private String imageName;
    private Long assetId;
}
