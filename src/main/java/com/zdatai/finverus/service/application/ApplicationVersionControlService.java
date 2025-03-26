package com.zdatai.finverus.service.application;

import com.zdatai.finverus.dto.application.ApplicationVersionControlDto;
import com.zdatai.finverus.dto.application.PaginatedResponse;
import com.zdatai.finverus.exception.FinVerusException;

public interface ApplicationVersionControlService {

    public void createNew() throws FinVerusException;

    public ApplicationVersionControlDto getLastVersionControl() throws FinVerusException;

    public PaginatedResponse<ApplicationVersionControlDto> getAllDetail() throws FinVerusException;

    public ApplicationVersionControlDto getVersionControlById(final Long recordId) throws FinVerusException;
}
