package com.edu.fpt.hoursemanager.management.building.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDetailResponse {
    private Long id;
    private String name;
    private String address;
    private String image;
    private String description;
    private String utilities;
    private String rules;
    private String rulesImage;
}
