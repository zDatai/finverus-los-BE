package com.zdatai.finverus.service.master;

import com.zdatai.finverus.request.application.master.ModelRequest;
import com.zdatai.finverus.response.master.ModelPageResponse;
import com.zdatai.finverus.response.master.ModelResponse;

public interface ModelService {
    void saveModel(ModelRequest modelRequest);
    ModelResponse getModelById (Long id);
    ModelPageResponse getModels(int page, int size);
    ModelResponse updateModel(Long id, ModelRequest modelRequest);
    ModelResponse getModelByMakeId(Long id);
}
