package com.zdatai.finverus.response.master;

import com.zdatai.finverus.model.AuditModifyUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssetCategoryResponse {
  private Long assetCategoryId;
  private String assetCategory;
  private AuditModifyUser.Status status;
  private VehicleClassResponse vehicle;
}
