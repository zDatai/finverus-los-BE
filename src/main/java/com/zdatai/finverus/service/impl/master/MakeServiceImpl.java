package com.zdatai.finverus.service.impl.master;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.master.Make;
import com.zdatai.finverus.repository.master.MakeRepository;
import com.zdatai.finverus.request.application.master.MakeRequest;
import com.zdatai.finverus.response.master.MakePageResponse;
import com.zdatai.finverus.response.master.MakeResponse;
import com.zdatai.finverus.service.master.MakeService;
import com.zdatai.finverus.utility.FinVerusSpecificationUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MakeServiceImpl implements MakeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MakeServiceImpl.class);
    private final MakeRepository makeRepository;
    private final MessageConfig config;

    @Override
    public MakePageResponse getAllMake(
            int pageNo, int pageSize, String sortBy, String sortDir, Map<String, String> queryParams) {

        LOGGER.info("Retrieve Make info all pagination(pageNo:{}, pageSize:{}, sortBy:{}, sortDir:{}, queryParams:{})",
                pageNo, pageSize, sortBy, sortDir, queryParams);
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Specification<Make> spec = (queryParams != null && !queryParams.isEmpty())
                ? FinVerusSpecificationUtil.getSpecification(queryParams)
                : Specification.where(null);

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Make> makePage = makeRepository.findAll(spec, pageable);

        LOGGER.info("Make data retrieved successfully. PageNo: {}, PageSize: {}, TotalElements: {}",
                makePage.getNumber(), makePage.getSize(), makePage.getTotalElements());

        return createPageResponse(makePage);
    }

    private MakePageResponse createPageResponse(Page<Make> makePage) {
        List<MakeResponse> contentList = makePage.getContent().stream()
                .map(make -> MakeResponse.builder()
                        .makeId(make.getMakeId())
                        .make(make.getMake())
                        .build())
                .toList();

        return MakePageResponse.builder()
                .content(contentList)
                .pageNo(makePage.getNumber())
                .pageSize(makePage.getSize())
                .totalElements(makePage.getTotalElements())
                .numberOfElements(makePage.getNumberOfElements())
                .totalPages(makePage.getTotalPages())
                .last(makePage.isLast())
                .build();
    }

    @Override
    @Transactional
    public void saveMake(MakeRequest makeRequest) throws FinVerusException {
        LOGGER.info("Creating make details: {}", makeRequest);

        try {
            makeRepository.save(Make.builder()
                    .make(makeRequest.getMake())
                    .build()
            );
        } catch (Exception e) {
            throw new FinVerusException(e.getMessage());
        }

        LOGGER.info("Created make details: {}", makeRequest);
    }

    @Override
    public MakeResponse getMakeById(Long id) {
        Optional<Make> makeById = makeRepository.findById(id);
        return getMakeResponse(makeById);
    }

    @Override
    public MakeResponse updateMake(Long id, MakeRequest makeRequest) {
        LOGGER.info("Updating make details: {}", makeRequest);

        Optional<Make> make = makeRepository.findById(id);

        if (make.isPresent()) {
            String[] ignoreList = {"makeId"};
            BeanUtils.copyProperties(makeRequest, make.get(), ignoreList);
            makeRepository.saveAndFlush(make.get());
        } else {
            throw new FinVerusException(config.getMessage("make.scheme.not.found"));
        }

        LOGGER.info("Updated make details: {}", makeRequest);
        return mapEntityToMake(make.get());
    }

    private MakeResponse mapEntityToMake(final Make make) {
        return MakeResponse.builder()
                .makeId(make.getMakeId())
                .make(make.getMake())
                .build();
    }

    private MakeResponse getMakeResponse(Optional<Make> make) {
        if (make.isPresent()) {
            return MakeResponse.builder()
                    .makeId(make.get().getMakeId())
                    .make(make.get().getMake())
                    .build();
        } else {
            throw new FinVerusException(config.getMessage("make.scheme.not.found"));
        }
    }
}
