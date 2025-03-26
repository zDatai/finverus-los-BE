package com.zdatai.finverus.controller.api.master;

import com.zdatai.finverus.constant.AppConstant;
import com.zdatai.finverus.dto.master.ProductSchemaDto;
import com.zdatai.finverus.dto.master.ProductSchemaUpdateDto;
import com.zdatai.finverus.dto.master.ProductSchemeCreateDto;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.service.master.ProductSchemeService;
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
@RequestMapping(value = "/api/${version}/public/product-scheme")
@RequiredArgsConstructor
@Tag(name = "Product Scheme Management", description = "APIs for managing product schemes")
public class ProductSchemeController {

    private final ProductSchemeService service;

    /**
     * Creates a new Product Scheme in the system based on the provided data.
     * <p>
     * This method receives a ProductSchemeCreateDto object, which contains the details of the product scheme
     * to be created, validates the input, and then delegates the creation process to the service layer.
     * The response is returned as an ApiResponse object indicating success or failure.
     *
     * @param newProductScheme The DTO containing the details of the new product scheme to be created.
     *                         It must be a valid ProductSchemeCreateDto object with all required fields.
     * @return A ResponseEntity containing an ApiResponse object with the status of the creation operation.
     *         If successful, the ApiResponse will contain a success message; if failed, an appropriate error message.
     */
    @PostMapping(value = "/create")
    @Operation(
            summary = "Create a new product scheme",
            description = "Creates a new product scheme with the provided details"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Product scheme created successfully",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    public ResponseEntity<ApiResponse<String>> create(
            @Parameter(description = "Product scheme details", required = true)
            @RequestBody @Valid final ProductSchemeCreateDto newProductScheme) {
        ApiResponse<String> response = service.create(newProductScheme);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing Product Scheme.
     *
     * @param productId The ID of the product scheme to be updated. Must be a valid positive number.
     * @param updateDto The request body containing the updated details.
     * @return ResponseEntity containing the update operation result.
     */
    @PutMapping(value = "/update/{productId}")
    @Operation(
            summary = "Update an existing product scheme",
            description = "Updates a product scheme with the specified ID with new details"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Product scheme updated successfully",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    public ResponseEntity<ApiResponse<String>> update(
            @Parameter(description = "Product scheme ID", required = true)
            @PathVariable final Long productId,
            @Parameter(description = "Updated product scheme details", required = true)
            @RequestBody @Valid final ProductSchemaUpdateDto updateDto) {
        ApiResponse<String> response = service.update(productId, updateDto);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves a Product Scheme by its ID.
     *
     * @param id The ID of the product scheme to retrieve. Must be a valid positive number.
     * @return ResponseEntity containing the product scheme details if found.
     */
    @GetMapping(value = "/get/{id}")
    @Operation(
            summary = "Get product scheme by ID",
            description = "Retrieves details of a specific product scheme by its ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Product scheme found",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    public ResponseEntity<ApiResponse<ProductSchemaDto>> getById(
            @Parameter(description = "Product scheme ID", required = true)
            @PathVariable final Long id){
        ApiResponse<ProductSchemaDto> response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves a paginated list of all Product Schemes with optional status filtering.
     *
     * @param page         The page number (default value defined in AppConstant).
     * @param size         The number of records per page (default value defined in AppConstant).
     * @param sortField    The field by which results should be sorted (default: productId).
     * @param sortDirection The sort direction (ASC or DESC, default: ASC).
     * @param status       (Optional) The status filter (ACTIVE or INACTIVE).
     * @return ResponseEntity containing a paginated list of product schemes.
     */
    @GetMapping(value = "/get/all")
    @Operation(
            summary = "Get all product schemes",
            description = "Retrieves a paginated list of product schemes with optional filtering by status"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved product schemes",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    public ResponseEntity<ApiResponse<Page<ProductSchemaDto>>> getAll(
            @Parameter(description = "Page number (zero-based)")
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_NO) final Integer page,
            @Parameter(description = "Number of items per page")
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_SIZE) final Integer size,
            @Parameter(description = "Field to sort by")
            @RequestParam(defaultValue = "productId") final String sortField,
            @Parameter(description = "Sort direction (ASC or DESC)")
            @RequestParam(defaultValue = "ASC") final String sortDirection,
            @Parameter(description = "Filter by status (ACTIVE or INACTIVE)")
            @RequestParam(required = false) AuditModifyUser.Status status
    ){
        Sort sort = sortDirection.equalsIgnoreCase("ASC") ?
                Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        ApiResponse<Page<ProductSchemaDto>> response;
        if(status != null){
            response = service.getAllByStatus(status, pageable);
        }else {
            response = service.getAllProductSchemes(pageable);
        }
        return ResponseEntity.ok(response);
    }
}
