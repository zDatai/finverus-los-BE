package com.zdatai.finverus.service.master;

import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.response.master.PurposeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurposeService {
    ApiResponse<Page<PurposeResponse>> getAllPurpose(Pageable pageable);

    ApiResponse<Page<PurposeResponse>> getAllByStatus(AuditModifyUser.Status status, Pageable pageable);
}


