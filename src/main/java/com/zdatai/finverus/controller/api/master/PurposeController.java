package com.zdatai.finverus.controller.api.master;

import com.zdatai.finverus.constant.AppConstant;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.master.Purpose;
import com.zdatai.finverus.response.master.AssetCategoryResponse;
import com.zdatai.finverus.response.master.PurposeResponse;
import com.zdatai.finverus.service.master.PurposeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/${version}/public/purpose")
public class PurposeController {
    private final PurposeService purposeService;

    @Operation( summary = "Retrieve All purpose",tags = {"Master Data"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Purpose.class))))
    })

    @GetMapping(value = "/get/all")
    public ResponseEntity<com.zdatai.finverus.response.ApiResponse<Page<PurposeResponse>>>getAll(
            @Parameter(description = "Page number (zero-based)")
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_NO) final Integer page,
            @Parameter(description = "Number of items per page")
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_SIZE) final Integer size,
            @Parameter(description = "Field to sort by")
            @RequestParam(defaultValue = "purposeId") final String sortField,
            @Parameter(description = "Sort direction (ASC or DESC)")
            @RequestParam(defaultValue = "ASC") final String sortDirection,
            @Parameter(description = "Filter by status (ACTIVE or INACTIVE)")
            @RequestParam(required = false) AuditModifyUser.Status status
    ){
        Sort sort =sortDirection.equalsIgnoreCase("ASC")?
                Sort.by(sortField).ascending():
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        com.zdatai.finverus.response.ApiResponse<Page<PurposeResponse>>response;

        if(status != null){
            response = purposeService.getAllByStatus(status,pageable);
        }else{
            response = purposeService.getAllPurpose(pageable);
        }
        return ResponseEntity.ok(response);
    }
}
