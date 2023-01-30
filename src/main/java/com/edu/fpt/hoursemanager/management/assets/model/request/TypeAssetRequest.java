package com.edu.fpt.hoursemanager.management.assets.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeAssetRequest {
    private Long id;
    private String code;
    private String name;
    private Long idBuilding;
}
