package com.edu.fpt.hoursemanager.management.building.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBuildingForm {
    private String id;
    private String name;
    private String city;
    private String district;
    private String wards;
    private String address;
    private String description;
    private String image;
    private String utilities;
    private String rules;
    private String rulesImage;
}
