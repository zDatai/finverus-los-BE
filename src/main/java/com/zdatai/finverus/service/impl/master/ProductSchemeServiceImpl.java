package com.zdatai.finverus.service.impl.master;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.dto.InputField;
import com.zdatai.finverus.dto.master.ProductSchemaUpdateDto;
import com.zdatai.finverus.dto.master.ProductSchemeCreateDto;
import com.zdatai.finverus.enums.ActivityTypeEnum;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.master.ProductScheme;
import com.zdatai.finverus.repository.master.ProductSchemeRepository;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.service.master.ProductSchemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductSchemeServiceImpl implements ProductSchemeService {

    private final ProductSchemeRepository repository;
    private final MessageConfig messageConfig;

    @Override
    public ApiResponse<String> create(ProductSchemeCreateDto newProductScheme) {

        ProductScheme productScheme = buildProductScheme(newProductScheme);
        Long productSchemeId = repository.save(productScheme).getProductId();

        if(productSchemeId == null) {
            return ApiResponse.error(messageConfig.getMessage("product.scheme.error"));
        }
        return ApiResponse.success(messageConfig.getMessage("product.scheme.success", productSchemeId));
    }

    @Override
    public ApiResponse<String> update(Long productId, ProductSchemaUpdateDto updateDto) {
        if(isProductSchemeExist(productId)){
            Optional<ProductScheme> productScheme = repository.findById(productId);
            if (productScheme.isPresent()) {
                if (isVersionMatch(productScheme.get().getVersion(), getTypedValue(updateDto.getVersion()))) {
                    productScheme.get().setProductName(getValue(updateDto.getProductName()));
                    productScheme.get().setProductType(getValue(updateDto.getProductType()));
                    productScheme.get().setAccountNoPrefix(getValue(updateDto.getAccountNoPrefix()));
                    productScheme.get().setActivityType(ActivityTypeEnum.valueOf(getValue(updateDto.getActivityType())));
                    productScheme.get().setStatus(AuditModifyUser.Status.valueOf(updateDto.getStatus().getValue()));
                    repository.save(productScheme.get());
                    return ApiResponse.success(messageConfig.getMessage("product.scheme.update.success", productId));
                }else {
                    return ApiResponse.error(messageConfig.getMessage("product.scheme.version.mismatch"));
                }
            }

        }

        return ApiResponse.error(messageConfig.getMessage("product.scheme.not.found", productId));
    }

    private Boolean isProductSchemeExist(Long productId){
        return repository.existsProductSchemeByProductId(productId);

    }
    private Boolean isVersionMatch(Integer exVersion, Integer newVersion){
        return exVersion.equals(newVersion);
    }

    private ProductScheme buildProductScheme(ProductSchemeCreateDto dto) {
        return ProductScheme.builder()
                .productName(getValue(dto.getProductName()))
                .productType(getValue(dto.getProductType()))
                .accountNoPrefix(getValue(dto.getAccountNoPrefix()))
                .activityType(ActivityTypeEnum.valueOf(getValue(dto.getActivityType())))
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
