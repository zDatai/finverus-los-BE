package com.zdatai.finverus.controller.api.master;


import com.zdatai.finverus.constant.AppConstant;
import com.zdatai.finverus.dto.master.ProductSubSchemeDto;
import com.zdatai.finverus.dto.master.ProductSubSchemeCreateDto;
import com.zdatai.finverus.dto.master.ProductSubSchemeUpdateDto;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.service.master.ProductSubSchemeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/${version}/public/product-sub-scheme")
@RequiredArgsConstructor
@Tag(name = "Product Sub-Scheme Management", description = "APIs for managing product sub-schemes")
public class ProductSubSchemeController {

    private final ProductSubSchemeService service;

    /**
     * Creates a new Product Sub-Scheme in the system based on the provided data.
     * <p>
     * This method receives a ProductSubSchemeCreateDto object, which contains the details of the product sub-scheme
     * to be created, validates the input, and then delegates the creation process to the service layer.
     * The response is returned as an ApiResponse object indicating success or failure.
     *
     * @param newProductSubScheme The DTO containing the details of the new product sub-scheme to be created.
     *                            It must be a valid ProductSubSchemeCreateDto object with all required fields.
     * @return A ResponseEntity containing an ApiResponse object with the status of the creation operation.
     *         If successful, the ApiResponse will contain a success message; if failed, an appropriate error message.
     */
    @PostMapping(value = "/create")
    @Operation(
            summary = "Create a new product sub-scheme",
            description = "Creates a new product sub-scheme with the provided details"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Product sub-scheme created successfully",
                    content = @Content(schema = @Schema(implementation = com.zdatai.finverus.response.ApiResponse.class))
            )
    })
    public ResponseEntity<ApiResponse<String>> create(
            @Parameter(description = "Product sub-scheme details", required = true)
            @RequestBody @Valid final ProductSubSchemeCreateDto newProductSubScheme) {
        ApiResponse<String> response = service.create(newProductSubScheme);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing Product Sub-Scheme.
     *
     * @param schemeId The ID of the product sub-scheme to be updated. Must be a valid positive number.
     * @param updateDto The request body containing the updated details.
     * @return ResponseEntity containing the update operation result.
     */
    @PutMapping(value = "/update/{schemeId}")
    @Operation(
            summary = "Update an existing product sub-scheme",
            description = "Updates a product sub-scheme with the specified ID with new details"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Product sub-scheme updated successfully",
                    content = @Content(schema = @Schema(implementation = com.zdatai.finverus.response.ApiResponse.class))
            )
    })
    public ResponseEntity<ApiResponse<String>> update(
            @Parameter(description = "Product sub-scheme ID", required = true)
            @PathVariable final Long schemeId,
            @Parameter(description = "Updated product sub-scheme details", required = true)
            @RequestBody @Valid final ProductSubSchemeUpdateDto updateDto) {
        ApiResponse<String> response = service.update(schemeId, updateDto);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves a Product Sub-Scheme by its ID.
     *
     * @param id The ID of the product sub-scheme to retrieve. Must be a valid positive number.
     * @return ResponseEntity containing the product sub-scheme details if found.
     */
    @GetMapping(value = "/get/{id}")
    @Operation(
            summary = "Get product sub-scheme by ID",
            description = "Retrieves details of a specific product sub-scheme by its ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Product sub-scheme found",
                    content = @Content(schema = @Schema(implementation = com.zdatai.finverus.response.ApiResponse.class))
            )
    })
    public ResponseEntity<ApiResponse<ProductSubSchemeDto>> getById(
            @Parameter(description = "Product sub-scheme ID", required = true)
            @PathVariable final Long id){
        ApiResponse<ProductSubSchemeDto> response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves a paginated list of all Product Sub-Schemes with optional status filtering.
     *
     * @param page The page number (default value defined in AppConstant).
     * @param size The number of records per page (default value defined in AppConstant).
     * @param sortField The field by which results should be sorted (default: schemeId).
     * @param sortDirection The sort direction (ASC or DESC, default: ASC).
     * @param status (Optional) The status filter (ACTIVE or INACTIVE).
     * @return ResponseEntity containing a paginated list of product sub-schemes.
     */
    @GetMapping(value = "/get/all")
    @Operation(
            summary = "Get all product sub-schemes",
            description = "Retrieves a paginated list of product sub-schemes with optional filtering by status"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved product sub-schemes",
                    content = @Content(schema = @Schema(implementation = com.zdatai.finverus.response.ApiResponse.class))
            )
    })
    public ResponseEntity<ApiResponse<Page<ProductSubSchemeDto>>> getAll(
            @Parameter(description = "Page number (zero-based)")
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_NO) final Integer page,
            @Parameter(description = "Number of items per page")
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_SIZE) final Integer size,
            @Parameter(description = "Field to sort by")
            @RequestParam(defaultValue = "schemeId") final String sortField,
            @Parameter(description = "Sort direction (ASC or DESC)")
            @RequestParam(defaultValue = "ASC") final String sortDirection,
            @Parameter(description = "Filter by status (ACTIVE or INACTIVE)")
            @RequestParam(required = false) AuditModifyUser.Status status
    ){
        Sort sort = sortDirection.equalsIgnoreCase("ASC") ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        ApiResponse<Page<ProductSubSchemeDto>> response;
        if(status != null){
            response = service.getAllByStatus(status, pageable);
        }else {
            response = service.getAllProductSubSchemes(pageable);
        }
        return ResponseEntity.ok(response);
    }
}