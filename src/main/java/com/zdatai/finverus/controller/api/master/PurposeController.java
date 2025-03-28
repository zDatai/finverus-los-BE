package com.zdatai.finverus.controller.api.master;

import com.zdatai.finverus.constant.AppConstant;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.master.Purpose;
import com.zdatai.finverus.request.master.PurposeRequest;
import com.zdatai.finverus.request.master.UpdatePurposeRequest;
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

    @Operation(
            summary = "Save new purpose From master Data Dashboard",
            tags = {"Master Data"})
    @ApiResponses(
            value = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Successfully Save new purpose",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            })

    @GetMapping(value = "/create")
    public ResponseEntity<com.zdatai.finverus.response.ApiResponse<String>>create(
            @RequestBody PurposeRequest purposeRequest){
        com.zdatai.finverus.response.ApiResponse<String> response = purposeService.save(purposeRequest);
        return ResponseEntity .ok(response);
    }

    @Operation(
            summary = "Update an existing purpose",
            description = "Updates purpose with the specified ID with new details"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "purpose updated successfully",
                    content = @Content(schema = @Schema(implementation = com.zdatai.finverus.response.ApiResponse.class))
            )

    })

    @PutMapping(value = "/update/{purposeId}")
    public ResponseEntity<com.zdatai.finverus.response.ApiResponse<String>>update(
            @PathVariable final Long purposeId,
            @RequestBody @Valid final UpdatePurposeRequest updatePurposeRequest){
        com.zdatai.finverus.response.ApiResponse<String> response = purposeService.update(purposeId,updatePurposeRequest);
        return ResponseEntity.ok(response);
    }
}
