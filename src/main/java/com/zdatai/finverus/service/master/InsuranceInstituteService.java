package com.zdatai.finverus.service.master;

import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.request.master.InsuranceInstituteRequest;
import com.zdatai.finverus.request.master.UpdateInsuranceInstituteRequest;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.response.master.InsuranceInstituteResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InsuranceInstituteService {

    ApiResponse<Page<InsuranceInstituteResponse>> getAllByStatus(AuditModifyUser.Status status, Pageable pageable);

    ApiResponse<Page<InsuranceInstituteResponse>> getAllInsuranceInstitute(Pageable pageable);

    InsuranceInstituteResponse getInsuranceById(Long insuranceId);

    ApiResponse<String> save(@Valid InsuranceInstituteRequest instituteRequest);

    ApiResponse<String> update(Long insuranceId, @Valid UpdateInsuranceInstituteRequest updateInsuranceInstituteRequest);
}
