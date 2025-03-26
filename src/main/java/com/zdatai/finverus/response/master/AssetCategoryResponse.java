package com.zdatai.finverus.response.master;

import com.zdatai.finverus.model.AuditModifyUser;
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
    private AuditModifyUser.Status status;
    private VehicleClassResponse vehicle;
}
