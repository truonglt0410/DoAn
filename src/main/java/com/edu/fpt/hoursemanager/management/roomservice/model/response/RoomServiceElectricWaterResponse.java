package com.edu.fpt.hoursemanager.management.roomservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomServiceElectricWaterResponse {
    private String nameRoom;
    private String amountElectric;
    private String amountWater;
    private String dateIncome;
    private String nameBuilding;
}
