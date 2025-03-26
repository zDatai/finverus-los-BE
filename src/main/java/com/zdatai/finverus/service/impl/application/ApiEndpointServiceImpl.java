package com.zdatai.finverus.service.impl.application;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.dto.application.ApiEndPointDto;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.repository.application.ApiEndpointRepository;
import com.zdatai.finverus.service.application.ApiEndpointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ApiEndpointServiceImpl implements ApiEndpointService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiEndpointServiceImpl.class);

    private final ApiEndpointRepository apiEndpointRepository;

    private final MessageConfig config;

    public ApiEndpointServiceImpl(ApiEndpointRepository apiEndpointRepository, MessageConfig config) {
        this.apiEndpointRepository = apiEndpointRepository;
        this.config = config;
    }

    @Override
    public void createNew(ApiEndPointDto apiEndPointDto) throws FinVerusException {

    }

    @Override
    public ApiEndPointDto update(Long recordId, ApiEndPointDto apiEndPointDto) throws FinVerusException {
        return null;
    }

    @Override
    public ApiEndPointDto getApiEndPointById(Long recordId) throws FinVerusException {
        return null;
    }

    @Override
    public ApiEndPointDto getApiEndPointBySourceId(Long sourceId) throws FinVerusException {
        return null;
    }
}
