package com.zdatai.finverus.service.impl.application;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.dto.application.ApplicationVersionControlDto;
import com.zdatai.finverus.dto.application.PaginatedResponse;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.repository.application.ApplicationVersionControlRepository;
import com.zdatai.finverus.service.application.ApplicationVersionControlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ApplicationVersionControlServiceImpl implements ApplicationVersionControlService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationVersionControlServiceImpl.class);

    private final ApplicationVersionControlRepository versionControlRepository;

    private final MessageConfig config;

    public ApplicationVersionControlServiceImpl(
            final ApplicationVersionControlRepository versionControlRepository,
            final MessageConfig config) {
        this.versionControlRepository = versionControlRepository;
        this.config = config;
    }

    @Override
    public void createNew() throws FinVerusException {

    }

    @Override
    public ApplicationVersionControlDto getLastVersionControl() throws FinVerusException {
        return null;
    }

    @Override
    public PaginatedResponse<ApplicationVersionControlDto> getAllDetail() throws FinVerusException {
        return null;
    }

    @Override
    public ApplicationVersionControlDto getVersionControlById(Long recordId) throws FinVerusException {
        return null;
    }
}
