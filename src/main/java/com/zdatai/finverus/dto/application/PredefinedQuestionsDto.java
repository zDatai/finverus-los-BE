package com.zdatai.finverus.dto.application;

import com.zdatai.finverus.dto.CommonAttributes;
import com.zdatai.finverus.enums.UIComponentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PredefinedQuestionsDto extends CommonAttributes {
    private OptionSourceDto optionSource;
    private String question;
    private Integer parent;
    private Integer sequence;
    private SectionDto section;
    private Integer shippable;
    private UIComponentTypeEnum inputType;
    private UIComponentTypeEnum responseType;
}
