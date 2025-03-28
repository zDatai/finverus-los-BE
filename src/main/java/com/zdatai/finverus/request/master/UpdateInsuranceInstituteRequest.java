package com.zdatai.finverus.request.master;

import com.zdatai.finverus.annotation.ValidateField;
import com.zdatai.finverus.dto.InputField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateInsuranceInstituteRequest  extends InsuranceInstituteRequest{
    @ValidateField(required = true, message = "Version is Required", expectedType = Integer.class)
    private InputField<Integer> version;
}
