package com.zdatai.finverus.dto.approval;

import com.zdatai.finverus.enums.ApplicationStatusEnum;
import com.zdatai.finverus.enums.ApplicationTypeEnum;
import com.zdatai.finverus.enums.CustomerTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ApplicationDto {
    private Integer applicationNumber;
    private ApplicationTypeEnum applicationType;
    private ApplicationStatusEnum applicationStatus;
    private BigDecimal applicationAmount;
    private BigDecimal totalAdvanceAmount;
    private CustomerTypeEnum customerType;
    private Long customerId;
    private Long productSchemeId;
    private Long productSubSchemeId;
    private Long agentId;
    private Long userAccountId;
}
