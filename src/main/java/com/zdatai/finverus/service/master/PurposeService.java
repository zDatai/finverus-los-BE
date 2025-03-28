package com.zdatai.finverus.service.master;

import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.request.master.PurposeRequest;
import com.zdatai.finverus.request.master.UpdatePurposeRequest;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.response.master.PurposeResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurposeService {
    ApiResponse<Page<PurposeResponse>> getAllPurpose(Pageable pageable);

    ApiResponse<Page<PurposeResponse>> getAllByStatus(AuditModifyUser.Status status, Pageable pageable);

    ApiResponse<String> save(PurposeRequest purposeRequest);

    ApiResponse<String> update(Long purposeId, @Valid UpdatePurposeRequest updatePurposeRequest);

}


