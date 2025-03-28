package com.zdatai.finverus.dto.master;

import com.zdatai.finverus.model.AuditModifyUser;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class ProductSubSchemeDto {
    private Long schemeId;
    private String schemeName;
    private LocalDate effectiveDate;
    private LocalDate expireDate;
    private AuditModifyUser.Status status;
    private Integer version;
}
