package com.zdatai.finverus.request.master;

import com.zdatai.finverus.annotation.ValidateField;
import com.zdatai.finverus.dto.InputField;
import com.zdatai.finverus.model.AuditModifyUser;
import lombok.Data;

@Data
public class AssetCategoryRequest {

    @ValidateField(required = true, message = "Asset Category Name is required", expectedType = String.class)
    private InputField<String> assetCategory;

    @ValidateField(required = true, message = "Asset Category Status is required", expectedType = String.class)
    private InputField<AuditModifyUser.Status> status;

    @ValidateField(required = true, message = "vehicale Class Id is required", expectedType = Long.class)
    private InputField<Long> vehicleClassId;
}
