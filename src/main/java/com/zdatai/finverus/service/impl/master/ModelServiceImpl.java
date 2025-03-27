package com.zdatai.finverus.service.impl.master;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.master.AssetCategory;
import com.zdatai.finverus.model.master.Make;
import com.zdatai.finverus.model.master.Model;
import com.zdatai.finverus.repository.master.AssetCategoryRepository;
import com.zdatai.finverus.repository.master.MakeRepository;
import com.zdatai.finverus.repository.master.ModelRepository;
import com.zdatai.finverus.request.application.master.ModelRequest;
import com.zdatai.finverus.response.master.ModelPageResponse;
import com.zdatai.finverus.response.master.ModelResponse;
import com.zdatai.finverus.service.master.ModelService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelServiceImpl.class);
    private final ModelRepository modelRepository;
    private final MakeRepository makeRepository;
    private final AssetCategoryRepository assetCategoryRepository;
    private final MessageConfig config;

    @Override
    @Transactional
    public void saveModel(ModelRequest modelRequest) {
        LOGGER.info("Creating model details: {}", modelRequest);

        Make make = makeRepository.findById(modelRequest.getMakeId().getTypedValue())
                .orElseThrow(() -> new FinVerusException(config.getMessage("make.scheme.not.found")));

        AssetCategory assetCategory = assetCategoryRepository.findById(modelRequest.getAssetCategoryId().getTypedValue())
                .orElseThrow(() -> new FinVerusException(config.getMessage("asset.category.not.found")));

        Model model = mapToModel(modelRequest, assetCategory, make);
        modelRepository.save(model);

        LOGGER.info("Created model details: {}", modelRequest);
    }

    private Model mapToModel(final ModelRequest modelRequest, final AssetCategory assetCategory, final Make make) {
        return Model.builder()
                .model(modelRequest.getModel().getTypedValue())
                .make(make)
                .status(AuditModifyUser.Status.ACTIVE)
                .assetCategory(assetCategory).build();
    }

    @Override
    public ModelResponse getModelById (Long id) {
        Optional<Model> model = modelRepository.findById(id);
        return getModelResponse(model);
    }

    private ModelResponse getModelResponse(Optional<Model> model) {
        if (model.isPresent()) {
            return ModelResponse.builder()
                    .modelId(model.get().getModelId())
                    .model(model.get().getModel())
                    .assetCategory(model.get().getAssetCategory().getAssetCategoryId())
                    .make(model.get().getMake().getMakeId())
                    .build();
        } else {
            throw new FinVerusException(config.getMessage("model.scheme.not.found"));
        }
    }

    @Override
    public ModelPageResponse getModels(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        Page<ModelResponse> modelResponses = modelRepository.getAllModels(pageRequest);

        LOGGER.info("Search model responses: {}", modelResponses.getContent());

        return ModelPageResponse.builder()
                .content(modelResponses.getContent().isEmpty() ? new ArrayList<>() : modelResponses.getContent())
                .pageNo(modelResponses.getNumber())
                .pageSize(modelResponses.getSize())
                .totalElements(modelResponses.getTotalElements())
                .numberOfElements(modelResponses.getTotalElements())
                .totalPages(modelResponses.getTotalPages())
                .last(modelResponses.isLast())
                .build();
    }

    @Override
    public ModelResponse updateModel(Long id, ModelRequest modelRequest) {
        LOGGER.info("Updating model details: {}", modelRequest);

        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new FinVerusException(config.getMessage("model.scheme.not.found")));

        Make make = makeRepository.findById(modelRequest.getMakeId().getTypedValue())
                .orElseThrow(() -> new FinVerusException(config.getMessage("make.scheme.not.found")));

        AssetCategory assetCategory = assetCategoryRepository.findById(modelRequest.getAssetCategoryId().getTypedValue())
                .orElseThrow(() -> new FinVerusException(config.getMessage("asset.category.not.found")));

        try {
            model.setMake(make);
            model.setAssetCategory(assetCategory);
            model.setModel(modelRequest.getModel().getTypedValue());
            return mapEntityToModel(modelRepository.save(model));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            LOGGER.info("Updated model details: {}", modelRequest);
        }
    }

    private ModelResponse mapEntityToModel(final Model model) {
        return ModelResponse.builder()
                .modelId(model.getModelId())
                .model(model.getModel())
                .assetCategory(model.getAssetCategory().getAssetCategoryId())
                .make(model.getMake().getMakeId())
                .build();
    }

}
