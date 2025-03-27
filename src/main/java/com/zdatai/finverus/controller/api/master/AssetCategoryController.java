package com.zdatai.finverus.controller.api.master;

import com.zdatai.finverus.constant.AppConstant;
import com.zdatai.finverus.dto.master.ProductSchemaUpdateDto;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.request.master.AssetCategoryRequest;
import com.zdatai.finverus.request.master.UpdateAssetCategoryRequest;
import com.zdatai.finverus.response.master.AssetCategoryResponse;
import com.zdatai.finverus.service.master.AssetCategoryService;
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
@RequestMapping(value = "/api/${version}/public/asset-category")
public class AssetCategoryController {

    private final AssetCategoryService assetCategoryService;

    @Operation( summary = "Retrieve All Asset Category",tags = {"Master Data"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(implementation = AssetCategoryResponse.class))))
    })

    @GetMapping(value = "/get/all")
    public ResponseEntity<com.zdatai.finverus.response.ApiResponse<Page<AssetCategoryResponse>>>getAll(
            @Parameter(description = "Page number (zero-based)")
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_NO) final Integer page,
            @Parameter(description = "Number of items per page")
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_SIZE) final Integer size,
            @Parameter(description = "Field to sort by")
            @RequestParam(defaultValue = "assetCategoryId") final String sortField,
            @Parameter(description = "Sort direction (ASC or DESC)")
            @RequestParam(defaultValue = "ASC") final String sortDirection,
            @Parameter(description = "Filter by status (ACTIVE or INACTIVE)")
            @RequestParam(required = false) AuditModifyUser.Status status
    ){
        Sort sort =sortDirection.equalsIgnoreCase("ASC")?
                Sort.by(sortField).ascending():
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page,size,sort);

        com.zdatai.finverus.response.ApiResponse<Page<AssetCategoryResponse>>response;

        if(status != null){
            response = assetCategoryService.getAllByStatus(status,pageable);
        }else{
            response = assetCategoryService.getAllAssetCategory(pageable);
        }
        return ResponseEntity.ok(response);

    }
    @Operation(summary = "Retrieve Asset category By Id", tags = {"Master Data"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved Asset category info by given Asset category id",
                    content = @Content(schema = @Schema(implementation = AssetCategoryResponse.class))
            )
    })
    @GetMapping(value = "/get/{assetCategoryId}")
    public ResponseEntity<AssetCategoryResponse> getAssetCategoryById(@PathVariable Long assetCategoryId) {
        AssetCategoryResponse response = assetCategoryService.getAssetCategoryById(assetCategoryId);
        return ResponseEntity.ok(response);
    }
    @Operation(
            summary = "Save new Asset category From master Data Dashboard",
            tags = {"Master Data"})
    @ApiResponses(
            value = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Successfully Save new Asset Type",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            })

    @PostMapping("/create")
    public ResponseEntity<com.zdatai.finverus.response.ApiResponse<String>> create(
            @RequestBody @Valid final AssetCategoryRequest assetCategoryRequest) {
        com.zdatai.finverus.response.ApiResponse<String> response = assetCategoryService.save(assetCategoryRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Update an existing Asset category",
            description = "Updates aAsset category with the specified ID with new details"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Asset category updated successfully",
                    content = @Content(schema = @Schema(implementation = com.zdatai.finverus.response.ApiResponse.class))
            )

    })
    @PutMapping(value = "/update/{assetCategoryId}")
    public ResponseEntity<com.zdatai.finverus.response.ApiResponse<String>> update(
            @PathVariable final Long assetCategoryId,
            @RequestBody @Valid final UpdateAssetCategoryRequest updateAssetCategoryRequest) {
        com.zdatai.finverus.response.ApiResponse<String> response = assetCategoryService.update(assetCategoryId, updateAssetCategoryRequest);
        return ResponseEntity.ok(response);
    }
}
