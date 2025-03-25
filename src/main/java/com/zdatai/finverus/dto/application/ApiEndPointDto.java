package com.zdatai.finverus.dto.application;

import com.zdatai.finverus.dto.CommonAttributes;
import com.zdatai.finverus.enums.HTTPMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ApiEndPointDto extends CommonAttributes {
    private Long sectionId;
    private String apiName;
    private String apiUrl;
    private HTTPMethod httpMethod;
    private String headerText;
    private List<ParameterTextDto> parameters;
}
