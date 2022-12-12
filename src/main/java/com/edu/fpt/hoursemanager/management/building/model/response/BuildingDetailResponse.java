package com.edu.fpt.hoursemanager.management.building.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDetailResponse {
    private Long id;
    private String name;
    private String address;
    private String image;
    private String description;
    private List<String> rules;
    private List<String> utilities;
    private String rulesImage;
    private Double longitude;
    private Double latitude;
}
