package com.zdatai.finverus.service.master;

import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.response.master.AssetCategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AssetCategoryService {
    ApiResponse<Page<AssetCategoryResponse>> getAllByStatus(AuditModifyUser.Status status, Pageable pageable);

    ApiResponse<Page<AssetCategoryResponse>> getAllAssetCategory(Pageable pageable);

    AssetCategoryResponse getAssetCategoryById(Long assetCategoryId);
}
