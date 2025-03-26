package com.zdatai.finverus.dto.master;

import com.zdatai.finverus.annotation.ValidateField;
import com.zdatai.finverus.dto.InputField;
import com.zdatai.finverus.model.AuditModifyUser;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ProductSchemaUpdateDto extends ProductSchemeCreateDto{

    @ValidateField(required = true, message = "Version is Required", expectedType = Integer.class)
    private InputField<Integer> version;

    @ValidateField(required = true,pattern = "^(ACTIVE|INACTIVE)$", message = "Status is Required",
            expectedType = String.class)
    private InputField<AuditModifyUser.Status> status;
}
