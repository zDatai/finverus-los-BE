package com.zdatai.finverus.request.master;

import com.zdatai.finverus.annotation.ValidateField;
import com.zdatai.finverus.dto.InputField;
import com.zdatai.finverus.model.AuditModifyUser;
import lombok.Data;

@Data
public class InsuranceInstituteRequest {
    @ValidateField(required = true, message = "Insurance Institute Name is required", expectedType = String.class)
    private InputField<String> insuranceInstituteName;

    @ValidateField(required = true, message = "Insurance Institute Status is required", expectedType = String.class)
    private InputField<AuditModifyUser.Status> status;
}
