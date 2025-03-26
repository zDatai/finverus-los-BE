package com.zdatai.finverus.service.master;

import com.zdatai.finverus.dto.master.ProductSchemaDto;
import com.zdatai.finverus.dto.master.ProductSchemaUpdateDto;
import com.zdatai.finverus.dto.master.ProductSchemeCreateDto;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductSchemeService {

    /**
     * Creates a new Product Scheme in the system based on the provided data.
     * <p>
     * This method takes a ProductSchemeCreateDto object containing the product scheme details,
     * validates the input, and processes the creation of the new product scheme in the repository.
     * If the creation is successful, it returns a success response with a message, including the ID of the created product scheme.
     * If there is an error, an error response is returned.
     *
     * @param newProductScheme The DTO containing the details of the product scheme to be created.
     *                         It must be a valid ProductSchemeCreateDto object.
     * @return An ApiResponse object containing the status of the product scheme creation operation.
     *         It will contain a success message with the product scheme ID or an error message if the creation fails.
     */
    ApiResponse<String> create(@Valid ProductSchemeCreateDto newProductScheme);

    /**
     * Updates an existing Product Scheme.
     *
     * @param productId The ID of the product scheme to be updated. Must be a valid positive number.
     * @param updateDto The request body containing the updated details, validated.
     * @return ApiResponse containing success message or error details.
     */
    ApiResponse<String> update(Long productId, @Valid ProductSchemaUpdateDto updateDto);

    /**
     * Retrieves a Product Scheme by its ID.
     *
     * @param id The ID of the product scheme to retrieve. Must be a valid positive number.
     * @return ApiResponse containing the product scheme details if found, or an error message if not found.
     */
    ApiResponse<ProductSchemaDto> getById(Long id);

    /**
     * Retrieves a paginated list of Product Schemes filtered by status.
     *
     * @param status   The status filter (ACTIVE or INACTIVE).
     * @param pageable The pagination and sorting information.
     * @return ApiResponse containing a paginated list of product schemes matching the status filter.
     */
    ApiResponse<Page<ProductSchemaDto>> getAllByStatus(AuditModifyUser.Status status, Pageable pageable);

    /**
     * Retrieves a paginated list of all Product Schemes.
     *
     * @param pageable The pagination and sorting information.
     * @return ApiResponse containing a paginated list of all product schemes.
     */
    ApiResponse<Page<ProductSchemaDto>> getAllProductSchemes(Pageable pageable);
}
