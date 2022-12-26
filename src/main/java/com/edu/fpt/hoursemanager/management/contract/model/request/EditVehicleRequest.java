package com.edu.fpt.hoursemanager.management.contract.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditVehicleRequest {
    private Long idVehicle;
    private String name;
    private String color;
    private String numberPlate;
    private String type;
    private Long contractId;
}
