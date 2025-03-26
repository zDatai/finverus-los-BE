package com.zdatai.finverus.service.application;

import com.zdatai.finverus.dto.application.ApiEndPointDto;
import com.zdatai.finverus.exception.FinVerusException;

public interface ApiEndpointService {

    public void createNew(final ApiEndPointDto apiEndPointDto) throws FinVerusException;
    public ApiEndPointDto update(final Long recordId,final ApiEndPointDto apiEndPointDto)
            throws FinVerusException;

    public ApiEndPointDto getApiEndPointById(final Long recordId) throws FinVerusException;

    public ApiEndPointDto getApiEndPointBySourceId(final Long sourceId) throws FinVerusException;

}
