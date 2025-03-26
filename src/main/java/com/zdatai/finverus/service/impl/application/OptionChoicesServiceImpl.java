package com.zdatai.finverus.service.impl.application;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.dto.application.OptionChoicesDto;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.application.OptionChoices;
import com.zdatai.finverus.repository.application.OptionChoicesRepository;
import com.zdatai.finverus.service.application.OptionChoicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OptionChoicesServiceImpl implements OptionChoicesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionChoicesServiceImpl.class);

    private final OptionChoicesRepository optionChoicesRepository;

    private final MessageConfig config;

    public OptionChoicesServiceImpl(
            final OptionChoicesRepository optionChoicesRepository,
            final MessageConfig config) {
        this.optionChoicesRepository = optionChoicesRepository;
        this.config = config;
    }

    @Override
    public void createNew(List<OptionChoicesDto> listChoices) throws FinVerusException {

    }

    @Override
    public OptionChoicesDto update(Long recordId, OptionChoicesDto apiEndPointDto) throws FinVerusException {
        return null;
    }

    @Override
    public OptionChoicesDto getApiEndPointById(Long recordId) throws FinVerusException {
        return null;
    }

    @Override
    public List<OptionChoicesDto> getApiEndPointBySourceId(Long sourceId) throws FinVerusException {
        return List.of();
    }

    @Override
    public List<OptionChoicesDto> getChoicesBySourceId(Long sourceId) throws FinVerusException {
        List<OptionChoices> listChoices = optionChoicesRepository.findByOptionSourceOptionSourceId(sourceId);

        return listChoices.stream()
                .map(e -> OptionChoicesDto.builder()
                        .sourceId(e.getOptionSource().getOptionSourceId())
                        .choiceValue(e.getDescription())
                        .recordId(e.getOptionChoiceId())
                        .build())
                .collect(Collectors.toList());
    }

}
