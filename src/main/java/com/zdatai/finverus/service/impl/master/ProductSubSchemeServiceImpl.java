package com.zdatai.finverus.service.impl.master;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.dto.InputField;
import com.zdatai.finverus.dto.master.ProductSubSchemeCreateDto;
import com.zdatai.finverus.dto.master.ProductSubSchemeDto;
import com.zdatai.finverus.dto.master.ProductSubSchemeUpdateDto;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.master.ProductSubScheme;
import com.zdatai.finverus.repository.master.ProductSubSchemeRepository;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.service.master.ProductSubSchemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductSubSchemeServiceImpl implements ProductSubSchemeService {
    private final ProductSubSchemeRepository repository;
    private final MessageConfig messageConfig;

    @Override
    public ApiResponse<String> create(ProductSubSchemeCreateDto createDto) {
        try {
            LocalDate effectiveDate = LocalDate.parse(getTypedValue(createDto.getEffectiveDate()));
            LocalDate expiredDate = LocalDate.parse(getTypedValue(createDto.getExpiredDate()));

            if (effectiveDate.isAfter(expiredDate)) {
                return ApiResponse.error(messageConfig.getMessage("product.sub.scheme.date.invalid"));
            }

            ProductSubScheme productSubScheme = buildProductSubScheme(createDto);
            Long schemeId = repository.save(productSubScheme).getSchemeId();

            if (schemeId == null) {
                return ApiResponse.error(messageConfig.getMessage("product.sub.scheme.error"));
            }
            return ApiResponse.success(messageConfig.getMessage("product.sub.scheme.success", schemeId));
        } catch (Exception e) {
            return ApiResponse.error(messageConfig.getMessage("product.sub.scheme.error") + ": " + e.getMessage());
        }
    }

    @Override
    public ApiResponse<String> update(Long schemeId, ProductSubSchemeUpdateDto updateDto) {
        if (isProductSubSchemeExist(schemeId)) {
            Optional<ProductSubScheme> productSubScheme = repository.findById(schemeId);
            if (productSubScheme.isPresent()) {
                if (isVersionMatch(productSubScheme.get().getVersion(), getTypedValue(updateDto.getVersion()))) {

                    LocalDate effectiveDate = LocalDate.parse(getTypedValue(updateDto.getEffectiveDate()));
                    LocalDate expiredDate = LocalDate.parse(getTypedValue(updateDto.getExpiredDate()));

                    if (effectiveDate.isAfter(expiredDate)) {
                        return ApiResponse.error(messageConfig.getMessage("product.sub.scheme.date.invalid"));
                    }

                    productSubScheme.get().setSchemeName(getValue(updateDto.getSchemeName()));
                    productSubScheme.get().setEffectiveDate(effectiveDate);
                    productSubScheme.get().setExpireDate(expiredDate);
                    productSubScheme.get().setStatus(AuditModifyUser.Status.valueOf(updateDto.getStatus().getValue()));

                    repository.save(productSubScheme.get());
                    return ApiResponse.success(messageConfig.getMessage("product.sub.scheme.update.success", schemeId));
                } else {
                    return ApiResponse.error(messageConfig.getMessage("product.sub.scheme.version.mismatch"));
                }
            }
        }

        return ApiResponse.error(messageConfig.getMessage("product.sub.scheme.not.found", schemeId));
    }

    @Override
    public ApiResponse<ProductSubSchemeDto> getById(Long id) {
        if (isProductSubSchemeExist(id)) {
            Optional<ProductSubScheme> productSubScheme = repository.findById(id);
            if (productSubScheme.isPresent()) {
                return ApiResponse.success(convertToDto(productSubScheme.get()));
            }
        }
        return ApiResponse.error(messageConfig.getMessage("product.sub.scheme.not.found", id));
    }

    @Override
    public ApiResponse<Page<ProductSubSchemeDto>> getAllProductSubSchemes(Pageable pageable) {
        Page<ProductSubScheme> productSubSchemePage = repository.findAll(pageable);
        Page<ProductSubSchemeDto> productSubSchemeDtoPage = productSubSchemePage.map(this::convertToDto);

        return ApiResponse.success(productSubSchemeDtoPage);
    }

    @Override
    public ApiResponse<Page<ProductSubSchemeDto>> getAllByStatus(AuditModifyUser.Status status, Pageable pageable) {
        Page<ProductSubScheme> productSubSchemePage = repository.findByStatus(status, pageable);
        Page<ProductSubSchemeDto> productSubSchemeDtoPage = productSubSchemePage.map(this::convertToDto);

        return ApiResponse.success(productSubSchemeDtoPage);
    }

    private ProductSubSchemeDto convertToDto(ProductSubScheme productSubScheme) {
        return ProductSubSchemeDto.builder()
                .schemeId(productSubScheme.getSchemeId())
                .schemeName(productSubScheme.getSchemeName())
                .effectiveDate(productSubScheme.getEffectiveDate())
                .expireDate(productSubScheme.getExpireDate())
                .status(productSubScheme.getStatus())
                .version(productSubScheme.getVersion())
                .build();
    }

    private Boolean isProductSubSchemeExist(Long schemeId) {
        return repository.existsById(schemeId);
    }

    private Boolean isVersionMatch(Integer exVersion, Integer newVersion) {
        return exVersion.equals(newVersion);
    }

    private ProductSubScheme buildProductSubScheme(ProductSubSchemeCreateDto dto) {
        return ProductSubScheme.builder()
                .schemeName(getValue(dto.getSchemeName()))
                .effectiveDate(LocalDate.parse(getTypedValue(dto.getEffectiveDate())))
                .expireDate(LocalDate.parse(getTypedValue(dto.getExpiredDate())))
                .status(AuditModifyUser.Status.ACTIVE)
                .build();
    }

    private <T> String getValue(InputField<T> inputField) {
        return inputField != null ? inputField.getValue() : null;
    }

    private <T> T getTypedValue(InputField<T> inputField) {
        return inputField != null ? inputField.getTypedValue() : null;
    }
}
