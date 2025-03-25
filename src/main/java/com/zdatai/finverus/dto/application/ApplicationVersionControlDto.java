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
public class ApplicationVersionControlDto extends CommonAttributes {
    private Long applicationVersionControlId;
}
