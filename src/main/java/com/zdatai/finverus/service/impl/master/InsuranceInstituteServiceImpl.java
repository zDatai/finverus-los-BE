package com.zdatai.finverus.service.impl.master;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.master.InsuranceInstitute;
import com.zdatai.finverus.repository.master.InsuranceInstituteRepository;
import com.zdatai.finverus.request.master.InsuranceInstituteRequest;
import com.zdatai.finverus.request.master.UpdateInsuranceInstituteRequest;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.response.master.InsuranceInstituteResponse;
import com.zdatai.finverus.service.master.InsuranceInstituteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InsuranceInstituteServiceImpl implements InsuranceInstituteService {

    private final InsuranceInstituteRepository insuranceInstituteRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceInstituteServiceImpl.class);
    private final MessageConfig config;

    public InsuranceInstituteServiceImpl(InsuranceInstituteRepository insuranceInstituteRepository, MessageConfig config) {
        this.insuranceInstituteRepository = insuranceInstituteRepository;
        this.config = config;
    }

    @Override
    public ApiResponse<Page<InsuranceInstituteResponse>> getAllInsuranceInstitute(Pageable pageable) {
        Page<InsuranceInstitute> insuranceInstitutePage = insuranceInstituteRepository.findAll(pageable);
        Page<InsuranceInstituteResponse> instituteResponsePage = insuranceInstitutePage.map(this::convertToResponse);
        return ApiResponse.success(instituteResponsePage);
    }

    @Override
    public InsuranceInstituteResponse getInsuranceById(Long insuranceId) {
        LOGGER.info("Fetching Insurance Institute details for Id: {}", insuranceId);
        InsuranceInstitute insuranceInstitute = insuranceInstituteRepository.findById(insuranceId)
                .orElseThrow(() -> {
                    LOGGER.error("Insurance Institute not found with Id: {}", insuranceId);
                    return new FinVerusException(config.getMessage("insurance.institute.not.found"));
                });
        return mapInsuranceInstituteResponse(insuranceInstitute);
    }

    @Override
    public ApiResponse<String> save(InsuranceInstituteRequest instituteRequest) {
        InsuranceInstitute insuranceInstitute = InsuranceInstitute.builder()
                .insuranceInstituteName(instituteRequest.getInsuranceInstituteName().getValue())
                .status(AuditModifyUser.Status.valueOf(instituteRequest.getStatus().getValue()))
                .build();

        insuranceInstituteRepository.save(insuranceInstitute);
        return ApiResponse.success(config.getMessage("insurance.institute.save.success"));
    }

    @Override
    public ApiResponse<String> update(Long insuranceId, UpdateInsuranceInstituteRequest updateInsuranceInstituteRequest) {
        Optional<InsuranceInstitute> optionalInsuranceInstitute = insuranceInstituteRepository.findById(insuranceId);

        if (optionalInsuranceInstitute.isPresent()) {
            InsuranceInstitute insuranceInstitute = optionalInsuranceInstitute.get();

            if (isVersionMatch(insuranceInstitute.getVersion(), getTypedValue(updateInsuranceInstituteRequest.getVersion().getTypedValue()))) {
                insuranceInstitute.setInsuranceInstituteName(updateInsuranceInstituteRequest.getInsuranceInstituteName().getValue());
                insuranceInstitute.setStatus(AuditModifyUser.Status.valueOf(updateInsuranceInstituteRequest.getStatus().getValue()));
                insuranceInstitute.setVersion(insuranceInstitute.getVersion() + 1);

                insuranceInstituteRepository.save(insuranceInstitute);
                return ApiResponse.success(config.getMessage("insurance.institute.update.success", insuranceId));
            } else {
                return ApiResponse.error(config.getMessage("insurance.institute.version.mismatch"));
            }
        } else {
            throw new FinVerusException(config.getMessage("insurance.institute.not.found"));
        }
    }

    @Override
    public ApiResponse<Page<InsuranceInstituteResponse>> getAllByStatus(AuditModifyUser.Status status, Pageable pageable) {
        Page<InsuranceInstitute> insuranceInstitutePage = insuranceInstituteRepository.findByStatus(status, pageable);
        Page<InsuranceInstituteResponse> instituteResponsePage = insuranceInstitutePage.map(this::convertToResponse);
        return ApiResponse.success(instituteResponsePage);
    }

    private InsuranceInstituteResponse mapInsuranceInstituteResponse(InsuranceInstitute insuranceInstitute) {
        return InsuranceInstituteResponse.builder()
                .insuranceInstituteId(insuranceInstitute.getInsuranceInstituteId())
                .insuranceInstituteName(insuranceInstitute.getInsuranceInstituteName())
                .status(insuranceInstitute.getStatus())
                .build();
    }

    private InsuranceInstituteResponse convertToResponse(InsuranceInstitute insuranceInstitute) {
        return InsuranceInstituteResponse.builder()
                .insuranceInstituteId(insuranceInstitute.getInsuranceInstituteId())
                .insuranceInstituteName(insuranceInstitute.getInsuranceInstituteName())
                .status(insuranceInstitute.getStatus())
                .build();
    }

    private boolean isVersionMatch(int existingVersion, int requestVersion) {
        return existingVersion == requestVersion;
    }

    private int getTypedValue(Integer value) {
        return value != null ? value : 0;
    }
}
