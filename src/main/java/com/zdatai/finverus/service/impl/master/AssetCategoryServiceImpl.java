package com.zdatai.finverus.service.impl.master;
import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.dto.InputField;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.master.AssetCategory;
import com.zdatai.finverus.repository.master.AssetCategoryRepository;
import com.zdatai.finverus.repository.master.VehicleRepository;
import com.zdatai.finverus.request.master.AssetCategoryRequest;
import com.zdatai.finverus.request.master.UpdateAssetCategoryRequest;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.response.master.AssetCategoryResponse;
import com.zdatai.finverus.service.master.AssetCategoryService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AssetCategoryServiceImpl implements AssetCategoryService {

  private final AssetCategoryRepository assetCategoryRepository;
  private final VehicleRepository vehicleRepository;
  private static final Logger LOGGER = LoggerFactory.getLogger(AssetCategoryServiceImpl.class);
  private final MessageConfig config;

  public AssetCategoryServiceImpl(
      AssetCategoryRepository assetCategoryRepository,
      VehicleRepository vehicleRepository,
      MessageConfig config) {
    this.assetCategoryRepository = assetCategoryRepository;
    this.vehicleRepository = vehicleRepository;
    this.config = config;
  }

  @Override
  public ApiResponse<Page<AssetCategoryResponse>> getAllByStatus(
      AuditModifyUser.Status status, Pageable pageable) {
    Page<AssetCategory> assetCategoryPage = assetCategoryRepository.findByStatus(status, pageable);
    Page<AssetCategoryResponse> assetCategoryResponsePage =
        assetCategoryPage.map(this::convertToResponse);

    return ApiResponse.success(assetCategoryResponsePage);
  }

  private AssetCategoryResponse convertToResponse(AssetCategory assetCategory) {
    return AssetCategoryResponse.builder()
        .assetCategoryId(assetCategory.getAssetCategoryId())
        .assetCategory(assetCategory.getAssetCategory())
        .vehicleClassId(assetCategory.getVehicleClass().getVehicalClassId())
        .status(assetCategory.getStatus())
        .build();
  }

  @Override
  public ApiResponse<Page<AssetCategoryResponse>> getAllAssetCategory(Pageable pageable) {
    Page<AssetCategory> assetCategoryPage = assetCategoryRepository.findAll(pageable);
    Page<AssetCategoryResponse> assetCategoryResponsePage =
        assetCategoryPage.map(this::convertToResponse);

    return ApiResponse.success(assetCategoryResponsePage);
  }

  @Override
  public AssetCategoryResponse getAssetCategoryById(Long assetCategoryId) {
    LOGGER.info("Fetching Asset category details for Id: {}", assetCategoryId);

    AssetCategory assetCategory =
        assetCategoryRepository
            .findById(assetCategoryId)
            .orElseThrow(
                () -> {
                  LOGGER.error("Asset category not found with Id: {}", assetCategoryId);
                  return new FinVerusException(config.getMessage("asset.category.not.found"));
                });

    return mapAssetCategoryResponse(assetCategory);
  }

  @Override
  public ApiResponse<String> save(AssetCategoryRequest assetCategoryRequest) {
    AssetCategory assetCategory =
        AssetCategory.builder()
            .assetCategory(assetCategoryRequest.getAssetCategory().getValue())
            .status(AuditModifyUser.Status.valueOf(assetCategoryRequest.getStatus().getValue()))
            .vehicleClass(
                vehicleRepository
                    .findById(assetCategoryRequest.getVehicleClassId().getTypedValue())
                    .orElseThrow(
                        () -> new FinVerusException(config.getMessage("vehicle.class.not.found"))))
            .build();

    assetCategoryRepository.save(assetCategory);
    return ApiResponse.success(config.getMessage("asset.category.save.success"));
  }

  @Override
  public ApiResponse<String> update(
      Long assetCategoryId, UpdateAssetCategoryRequest updateAssetCategoryRequest) {
    if (isAssetCategoryExist(assetCategoryId)) {
      Optional<AssetCategory> assetCategory = assetCategoryRepository.findById(assetCategoryId);
      if (assetCategory.isPresent()) {
        if (isVersionMatch(
            assetCategory.get().getVersion(),
            getTypedValue(updateAssetCategoryRequest.getVersion()))) {
          assetCategory
              .get()
              .setAssetCategory(getValue(updateAssetCategoryRequest.getAssetCategory()));
          assetCategory
              .get()
              .setVehicleClass(
                  vehicleRepository
                      .findById(updateAssetCategoryRequest.getVehicleClassId().getTypedValue())
                      .orElseThrow(
                          () ->
                              new FinVerusException(config.getMessage("vehicle.class.not.found"))));
          assetCategory.get().setVersion(assetCategory.get().getVersion() + 1);
          assetCategory
              .get()
              .setStatus(
                  AuditModifyUser.Status.valueOf(
                      updateAssetCategoryRequest.getStatus().getValue()));
          assetCategoryRepository.save(assetCategory.get());
          return ApiResponse.success(
              config.getMessage("asset.category.update.success", assetCategoryId));
        } else {
          return ApiResponse.error(config.getMessage("asset.category.version.mismatch"));
        }
      }
    }

    return ApiResponse.error(config.getMessage("product.scheme.not.found", assetCategoryId));
  }

  private Boolean isVersionMatch(Integer exVersion, Integer newVersion) {
    return exVersion.equals(newVersion);
  }

  private <T> String getValue(InputField<T> inputField) {
    return inputField != null ? inputField.getValue() : null;
  }

  private <T> T getTypedValue(InputField<T> inputField) {
    return inputField != null ? inputField.getTypedValue() : null;
  }

  private boolean isAssetCategoryExist(Long assetCategoryId) {
    return assetCategoryRepository.existsById(assetCategoryId);
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
