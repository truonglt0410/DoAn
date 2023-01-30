package com.edu.fpt.hoursemanager.management.roomservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {
    private Long idService;
    private String nameService;
    private Double priceService;
    private Long idBuilding;
    private String typePayment;
    private boolean isElectric;
}
