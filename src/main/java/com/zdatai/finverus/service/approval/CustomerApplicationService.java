package com.zdatai.finverus.service.approval;

import com.zdatai.finverus.dto.approval.ApplicationDto;
import com.zdatai.finverus.exception.FinVerusException;

public interface CustomerApplicationService {
    void createApplication(final ApplicationDto applicationDto) throws FinVerusException;
}
