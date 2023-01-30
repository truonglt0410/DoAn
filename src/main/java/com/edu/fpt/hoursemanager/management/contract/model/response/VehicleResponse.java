package com.edu.fpt.hoursemanager.management.contract.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponse {
    private Long id;
    private String name;
    private String color;
    private String numberPlate;
    private String type;
    private Long idRoom;
}
