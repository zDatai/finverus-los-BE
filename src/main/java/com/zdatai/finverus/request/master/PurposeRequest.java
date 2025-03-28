package com.zdatai.finverus.request.master;

import com.zdatai.finverus.annotation.ValidateField;
import com.zdatai.finverus.dto.InputField;
import com.zdatai.finverus.model.AuditModifyUser;
import lombok.Data;

@Data
public class PurposeRequest {
    @ValidateField(required = true,message = "Purpose name is requierd ",expectedType = String.class)
    private InputField<String> purpose;

    @ValidateField(required = true, message = "Purpose Status is required", expectedType = String.class)
    private InputField<AuditModifyUser.Status> status;
}
