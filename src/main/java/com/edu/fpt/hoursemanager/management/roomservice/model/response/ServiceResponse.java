package com.edu.fpt.hoursemanager.management.roomservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse {
    private Long id;
    private String name;
    private Double price;
    private String typePayment;
    private Long idBuilding;

    public ServiceResponse(Long id, String name, Double price, String typePayment) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.typePayment = typePayment;
    }
}
