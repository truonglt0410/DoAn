package com.edu.fpt.hoursemanager.management.building.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingResponse {
    private Long id;
    private String name;
    private String address;
    private String image;
    private Double longitude;
    private Double latitude;
    private Long accountId;
    private String imageName;
}
