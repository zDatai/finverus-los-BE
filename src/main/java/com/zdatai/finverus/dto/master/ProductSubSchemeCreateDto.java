package com.zdatai.finverus.dto.master;

import com.zdatai.finverus.annotation.ValidateField;
import com.zdatai.finverus.dto.InputField;
import lombok.Data;



@Data
public class ProductSubSchemeCreateDto {

    @ValidateField(required = true, message = "Scheme Name is required", expectedType = String.class)
    private InputField<String> schemeName;

    @ValidateField(required = true, message = "Effective Date is required", expectedType = String.class)
    private InputField<String> effectiveDate;

    @ValidateField(required = true, message = "Expired Date is required and must be after Effective Date",
            expectedType = String.class)
    private InputField<String> expiredDate;
}
