package com.zdatai.finverus.dto.application;

import com.zdatai.finverus.dto.CommonAttributes;
import com.zdatai.finverus.enums.SourceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class OptionSourceDto extends CommonAttributes {
    private SourceTypeEnum sourceType;
    private String description;
    private List<OptionChoicesDto> choices;
    private String apiEndPointResponse;
}
