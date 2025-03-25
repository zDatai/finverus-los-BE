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
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SectionDto extends CommonAttributes {
    private String sectionName;
    private Integer sequence;
    private ApplicationVersionControlDto appVersion;
}
