package com.edu.fpt.hoursemanager.management.roomservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicRoomServiceResponse {
    private Long id;
    private String name;
    private Double price;
    private Double amount;
    private Double sumPrice;
    private String typePayment;
    private Long idBuilding;

}
