package com.zdatai.finverus.service.application;

import com.zdatai.finverus.dto.application.OptionChoicesDto;
import com.zdatai.finverus.exception.FinVerusException;

import java.util.List;

public interface OptionChoicesService {
    public void createNew(final List<OptionChoicesDto> listChoices) throws FinVerusException;

    public OptionChoicesDto update(final Long recordId, final OptionChoicesDto apiEndPointDto)
            throws FinVerusException;

    public OptionChoicesDto getApiEndPointById(final Long recordId) throws FinVerusException;

    public List<OptionChoicesDto> getApiEndPointBySourceId(final Long sourceId) throws FinVerusException;

    public List<OptionChoicesDto> getChoicesBySourceId(final Long sourceId) throws FinVerusException;
}
