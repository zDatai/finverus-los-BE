package com.zdatai.finverus.dto.application;

import com.zdatai.finverus.dto.CommonAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class OptionChoicesDto extends CommonAttributes {
    private Long questionId;
    private Long sourceId;
    private String choiceValue;
}
