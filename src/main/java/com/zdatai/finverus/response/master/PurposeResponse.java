package com.zdatai.finverus.response.master;

import com.zdatai.finverus.model.AuditModifyUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurposeResponse {
    private Long purposeId;
    private String purpose;
    private AuditModifyUser.Status status;
}
