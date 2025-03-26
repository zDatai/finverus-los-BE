package com.zdatai.finverus.request.application;

import com.zdatai.finverus.dto.application.OptionSourceDto;
import com.zdatai.finverus.dto.application.SectionDto;
import com.zdatai.finverus.enums.UIComponentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredefinedQuestionRequest {
    private OptionSourceDto optionSource;
    private String question;
    private Integer parent;
    private Integer sequence;
    private SectionDto section;
    private Integer shippable;
    private UIComponentTypeEnum inputType;
    private UIComponentTypeEnum responseType;
}
