package com.edu.fpt.hoursemanager.management.assets.model.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTypeAssetsResponseFromQuery {
    private Long idTypeAsset;
    private String nameTypeAsset;
    private String codeTypeAsset;
    private Long idAsset;
}
