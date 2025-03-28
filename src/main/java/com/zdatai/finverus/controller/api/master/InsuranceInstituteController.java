package com.zdatai.finverus.controller.api.master;

import com.zdatai.finverus.constant.AppConstant;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.request.master.AssetCategoryRequest;
import com.zdatai.finverus.request.master.InsuranceInstituteRequest;
import com.zdatai.finverus.request.master.UpdateAssetCategoryRequest;
import com.zdatai.finverus.request.master.UpdateInsuranceInstituteRequest;
import com.zdatai.finverus.response.master.AssetCategoryResponse;
import com.zdatai.finverus.response.master.InsuranceInstituteResponse;
import com.zdatai.finverus.service.master.InsuranceInstituteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/${version}/public/insurance/institute")
public class InsuranceInstituteController {

    private final InsuranceInstituteService insuranceInstituteService;

    @Operation( summary = "Retrieve All Insurance Institute",tags = {"Master Data"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = ApiResponse.class))))
    })

    @GetMapping(value = "/get/all")
    public ResponseEntity<com.zdatai.finverus.response.ApiResponse<Page<InsuranceInstituteResponse>>>getAll(
            @Parameter(description = "Page number (zero-based)")
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_NO) final Integer page,
            @Parameter(description = "Number of items per page")
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_SIZE) final Integer size,
            @Parameter(description = "Field to sort by")
            @RequestParam(defaultValue = "insuranceInstituteId") final String sortField,
            @Parameter(description = "Sort direction (ASC or DESC)")
            @RequestParam(defaultValue = "ASC") final String sortDirection,
            @Parameter(description = "Filter by status (ACTIVE or INACTIVE)")
            @RequestParam(required = false) AuditModifyUser.Status status
    ){
        Sort sort =sortDirection.equalsIgnoreCase("ASC")?
                Sort.by(sortField).ascending():
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        com.zdatai.finverus.response.ApiResponse<Page<InsuranceInstituteResponse>>response;

        if(status != null){
            response = insuranceInstituteService.getAllByStatus(status,pageable);
        }else{
            response = insuranceInstituteService.getAllInsuranceInstitute(pageable);
        }
        return ResponseEntity.ok(response);

    }

    @Operation(summary = "Retrieve Insurance Institute By Id", tags = {"Master Data"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved Insurance Institute info by given Insurance Institute id",
                    content = @Content(schema = @Schema(implementation = AssetCategoryResponse.class))
            )
    })

    @GetMapping(value = "/get/{insuranceId}")
    public ResponseEntity<InsuranceInstituteResponse>getInsuranceById(
            @PathVariable final Long insuranceId){
        InsuranceInstituteResponse response = insuranceInstituteService.getInsuranceById(insuranceId);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Save new Insurance Institute From master Data Dashboard",
            tags = {"Master Data"})
    @ApiResponses(
            value = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Successfully Save new Insurance Institute",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            })

    @PostMapping("/create")
    public ResponseEntity<com.zdatai.finverus.response.ApiResponse<String>> create(
            @RequestBody @Valid final InsuranceInstituteRequest instituteRequest) {
        com.zdatai.finverus.response.ApiResponse<String> response = insuranceInstituteService.save(instituteRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Update an existing Insurance Institute",
            description = "Updates Insurance Institute with the specified ID with new details"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Insurance Institute updated successfully",
                    content = @Content(schema = @Schema(implementation = com.zdatai.finverus.response.ApiResponse.class))
            )

    })
    @PutMapping(value = "/update/{insuranceId}")
    public ResponseEntity<com.zdatai.finverus.response.ApiResponse<String>> update(
            @PathVariable final Long insuranceId,
            @RequestBody @Valid final UpdateInsuranceInstituteRequest updateInsuranceInstituteRequest) {
        com.zdatai.finverus.response.ApiResponse<String> response = insuranceInstituteService.update(insuranceId, updateInsuranceInstituteRequest);
        return ResponseEntity.ok(response);
    }

}
