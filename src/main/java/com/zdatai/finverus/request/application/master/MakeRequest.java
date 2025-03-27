package com.zdatai.finverus.request.application.master;

import com.zdatai.finverus.annotation.ValidateField;
import com.zdatai.finverus.dto.InputField;
import lombok.Data;

@Data
public class MakeRequest {
    @ValidateField(required = true, message = "Make Name is Required", expectedType = String.class)
    private InputField<String> make;
}
