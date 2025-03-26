package com.zdatai.finverus.service.impl.master;

import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.master.AssetCategory;
import com.zdatai.finverus.repository.master.AssetCategoryRepository;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.response.master.AssetCategoryResponse;
import com.zdatai.finverus.service.master.AssetCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AssetCategoryServiceImpl  implements AssetCategoryService {

    private final AssetCategoryRepository assetCategoryRepository;

    public AssetCategoryServiceImpl(AssetCategoryRepository assetCategoryRepository) {
        this.assetCategoryRepository = assetCategoryRepository;
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
}
