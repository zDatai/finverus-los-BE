package com.zdatai.finverus.response.master;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetCategoryResponse {
    private Long assetCategoryId;
    private String assetCategory;
    private VehicleClassResponse vehicle;
}
