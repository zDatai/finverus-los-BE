package com.zdatai.finverus.service.impl.master;

import com.zdatai.finverus.model.master.Make;
import com.zdatai.finverus.repository.master.MakeRepository;
import com.zdatai.finverus.response.master.MakePageResponse;
import com.zdatai.finverus.response.master.MakeResponse;
import com.zdatai.finverus.service.master.MakeService;
import com.zdatai.finverus.utility.FinVerusSpecificationUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MakeServiceImpl implements MakeService {

    private final MakeRepository makeRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(MakeServiceImpl.class);

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
}
