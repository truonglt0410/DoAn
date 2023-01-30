package com.edu.fpt.hoursemanager.management.building.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBuildingRequest {
    private String name;
    private String address;
    private String description;
    private String image;
    private String utilities;
    private String rules;
    private String rulesImage;
    private Double longitude;
    private Double latitude;
    private String imageName;
}
