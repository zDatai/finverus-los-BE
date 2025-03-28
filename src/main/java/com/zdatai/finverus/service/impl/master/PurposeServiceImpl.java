package com.zdatai.finverus.service.impl.master;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.master.Purpose;
import com.zdatai.finverus.repository.master.PurposeRepository;
import com.zdatai.finverus.request.master.PurposeRequest;
import com.zdatai.finverus.request.master.UpdatePurposeRequest;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.response.master.PurposeResponse;
import com.zdatai.finverus.service.master.PurposeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PurposeServiceImpl implements PurposeService {

    private final PurposeRepository purposeRepository;
    private final MessageConfig config;

    public PurposeServiceImpl(PurposeRepository purposeRepository, MessageConfig config) {
        this.purposeRepository = purposeRepository;
        this.config = config;
    }

    @Override
    public ApiResponse<Page<PurposeResponse>> getAllPurpose(Pageable pageable) {
        Page<Purpose> purposePage = purposeRepository.findAll(pageable);
        Page<PurposeResponse> purposeResponsePage = purposePage.map(this::convertToResponse);
        return ApiResponse.success(purposeResponsePage);
    }

    @Override
    public ApiResponse<Page<PurposeResponse>> getAllByStatus(AuditModifyUser.Status status, Pageable pageable) {
        Page<Purpose> purposePage = purposeRepository.findByStatus(status, pageable);
        Page<PurposeResponse> purposeResponsePage = purposePage.map(this::convertToResponse);
        return ApiResponse.success(purposeResponsePage);
    }

    @Override
    public ApiResponse<String> save(PurposeRequest purposeRequest) {
        Purpose purpose =
                Purpose.builder()
                        .purpose(purposeRequest.getPurpose().getValue())
                        .status(AuditModifyUser.Status.valueOf(purposeRequest.getStatus().getValue()))
                        .build();
        purposeRepository.save(purpose);

        return ApiResponse.success(config.getMessage("purpose.save.success"));
    }

    @Override
    public ApiResponse<String> update(Long purposeId, UpdatePurposeRequest updatePurposeRequest) {
        return null;
    }


    private PurposeResponse convertToResponse(Purpose purpose) {
        return PurposeResponse.builder()
                .purposeId(purpose.getPurposeId())
                .purpose(purpose.getPurpose())
                .status(purpose.getStatus())
                .build();
    }
}
