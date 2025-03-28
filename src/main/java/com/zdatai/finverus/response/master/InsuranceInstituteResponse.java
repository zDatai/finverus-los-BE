package com.zdatai.finverus.response.master;

import com.zdatai.finverus.model.AuditModifyUser;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class InsuranceInstituteResponse {
    private Long insuranceInstituteId;
    private String insuranceInstituteName;
    private AuditModifyUser.Status status;


}
