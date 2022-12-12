package com.edu.fpt.hoursemanager.management.assets.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseResponse {
    private Long idWarehouse;
    private String nameWarehouse;
}
