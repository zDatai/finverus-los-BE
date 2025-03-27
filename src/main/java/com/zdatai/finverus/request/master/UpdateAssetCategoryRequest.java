package com.zdatai.finverus.request.master;

import com.zdatai.finverus.annotation.ValidateField;
import com.zdatai.finverus.dto.InputField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
public class UpdateAssetCategoryRequest extends AssetCategoryRequest{
    @ValidateField(required = true, message = "Version is Required", expectedType = Integer.class)
    private InputField<Integer> version;


}
