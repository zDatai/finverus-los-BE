package com.zdatai.finverus.dto.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zdatai.finverus.annotation.ValidateField;
import com.zdatai.finverus.dto.InputField;
import com.zdatai.finverus.enums.ActivityTypeEnum;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSchemeCreateDto {

    @ValidateField(required = true,message = "Product Name is Required", expectedType = String.class)
    private InputField<String> productName;

    @ValidateField(required = true,pattern = "^(CONVENTIONAL|ISLAMIC|GENERAL)$", message = "Activity Type is Required",
            expectedType = String.class)
    private InputField<ActivityTypeEnum> activityType;

    @ValidateField(required = true, message = "Product Type is Required", expectedType = String.class)
    private InputField<String> productType;

    @ValidateField(required = true, message = "Account No Prefix is Required", expectedType = String.class)
    private InputField<String> accountNoPrefix;

}
