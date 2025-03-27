package com.zdatai.finverus.service.impl.master;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.master.AssetCategory;
import com.zdatai.finverus.repository.master.AssetCategoryRepository;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.response.master.AssetCategoryResponse;
import com.zdatai.finverus.service.master.AssetCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AssetCategoryServiceImpl  implements AssetCategoryService {

    private final AssetCategoryRepository assetCategoryRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(AssetCategoryServiceImpl.class);
    private final MessageConfig config;

    public AssetCategoryServiceImpl(AssetCategoryRepository assetCategoryRepository, MessageConfig config) {
        this.assetCategoryRepository = assetCategoryRepository;
        this.config = config;
    }

    @Override
    public ApiResponse<Page<AssetCategoryResponse>> getAllByStatus(AuditModifyUser.Status status, Pageable pageable) {
        Page<AssetCategory> assetCategoryPage = assetCategoryRepository.findByStatus(status, pageable);
        Page<AssetCategoryResponse> assetCategoryResponsePage = assetCategoryPage.map(this::convertToResponse);

        return ApiResponse.success(assetCategoryResponsePage);
    }

    private AssetCategoryResponse convertToResponse(AssetCategory assetCategory) {
        return AssetCategoryResponse.builder()
                .assetCategoryId(assetCategory.getAssetCategoryId())
                .assetCategory(assetCategory.getAssetCategory())
                .status(assetCategory.getStatus())
                .build();
    }

    @Override
    public ApiResponse<Page<AssetCategoryResponse>> getAllAssetCategory(Pageable pageable) {
        Page<AssetCategory> assetCategoryPage = assetCategoryRepository.findAll(pageable);
        Page<AssetCategoryResponse> assetCategoryResponsePage = assetCategoryPage.map(this::convertToResponse);

        return ApiResponse.success(assetCategoryResponsePage);
    }

    @Override
    public AssetCategoryResponse getAssetCategoryById(Long assetCategoryId) {
        LOGGER.info("Fetching Asset category details for Id: {}", assetCategoryId);

        AssetCategory assetCategory = assetCategoryRepository.findById(assetCategoryId)
                .orElseThrow(() -> {
                    LOGGER.error("Asset category not found with Id: {}", assetCategoryId);
                    return new FinVerusException(config.getMessage("asset.category.not.found"));
                });

        return mapAssetCategoryResponse(assetCategory);
    }
    private AssetCategoryResponse mapAssetCategoryResponse(AssetCategory assetCategory) {
        return AssetCategoryResponse.builder()
                .assetCategoryId(assetCategory.getAssetCategoryId())
                .assetCategory(assetCategory.getAssetCategory())
                .vehicleClassId(assetCategory.getVehicleClass().getVehicalClassId())
                .status(assetCategory.getStatus())
                .build();
    }

}
