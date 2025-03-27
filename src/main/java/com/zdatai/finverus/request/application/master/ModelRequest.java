package com.zdatai.finverus.request.application.master;

import com.zdatai.finverus.annotation.ValidateField;
import com.zdatai.finverus.dto.InputField;
import lombok.Data;

@Data
public class ModelRequest {
    @ValidateField(required = true, message = "Model Name is Required", expectedType = String.class)
    private InputField<String> model;

    @ValidateField(required = true, message = "Asset category is Required", expectedType = Long.class)
    private InputField<Long> assetCategoryId;

    @ValidateField(required = true, message = "Make is Required", expectedType = Long.class)
    private InputField<Long> makeId;
}
