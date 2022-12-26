package com.edu.fpt.hoursemanager.management.room.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailAssetsResponse {
    private String assetsName;
    private String color;
    private String model;
}
