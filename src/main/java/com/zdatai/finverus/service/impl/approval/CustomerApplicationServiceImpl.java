package com.zdatai.finverus.service.impl.approval;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.dto.approval.ApplicationDto;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.approval.Application;
import com.zdatai.finverus.model.master.*;
import com.zdatai.finverus.model.user.UserAccount;
import com.zdatai.finverus.repository.approval.ApplicationRepository;
import com.zdatai.finverus.repository.master.AgentRepository;
import com.zdatai.finverus.repository.master.ProductSchemeRepository;
import com.zdatai.finverus.repository.master.ProductSubSchemeRepository;
import com.zdatai.finverus.repository.user.UserAccountRepository;
import com.zdatai.finverus.service.approval.CustomerApplicationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerApplicationServiceImpl implements CustomerApplicationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerApplicationServiceImpl.class);
    private final MessageConfig config;
    private final ApplicationRepository applicationRepository;
    private final ProductSchemeRepository productSchemeRepository;
    private final ProductSubSchemeRepository productSubSchemeRepository;
    private final AgentRepository agentRepository;
    private final UserAccountRepository userAccountRepository;

    @Override
    @Transactional
    public void createApplication(final ApplicationDto applicationDto) throws FinVerusException {
        LOGGER.info("Creating application details: {}", applicationDto);

        ProductScheme productScheme = productSchemeRepository.findById(applicationDto.getProductSchemeId())
                .orElseThrow(() -> new FinVerusException(config.getMessage("product.scheme.not.found")));

        ProductSubScheme productSubScheme = productSubSchemeRepository.findById(applicationDto.getProductSubSchemeId())
                .orElseThrow(() -> new FinVerusException(config.getMessage("product.sub.scheme.not.found")));

        Agent agent = Optional.ofNullable(applicationDto.getAgentId())
                .map(id -> agentRepository.findById(id)
                        .orElseThrow(() -> new FinVerusException(config.getMessage("agent.not.found"))))
                .orElse(null);

        UserAccount userAccount = Optional.ofNullable(applicationDto.getUserAccountId())
                .map(id -> userAccountRepository.findById(id)
                        .orElseThrow(() -> new FinVerusException(config.getMessage("user.not.found"))))
                .orElse(null);

        Application application = mapToApplication(applicationDto, productScheme, productSubScheme, agent, userAccount);
        applicationRepository.save(application);

        LOGGER.info("Created application details: {}", applicationDto);
    }

    private Application mapToApplication(final ApplicationDto applicationDto, ProductScheme product,
                                         ProductSubScheme productSub, Agent agent, UserAccount user) {
        return Application.builder()
                .applicationNumber(applicationDto.getApplicationNumber())
                .applicationType(applicationDto.getApplicationType())
                .applicationStatus(applicationDto.getApplicationStatus())
                .applicationAmount(applicationDto.getApplicationAmount())
                .totalAdvanceAmount(applicationDto.getTotalAdvanceAmount())
                .customerType(applicationDto.getCustomerType())
                .customerId(applicationDto.getCustomerId())
                .productScheme(product)
                .productSubScheme(productSub)
                .agent(agent)
                .userAccount(user)
                .build();
    }
}
