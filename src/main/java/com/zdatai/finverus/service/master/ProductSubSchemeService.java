package com.zdatai.finverus.service.master;

import com.zdatai.finverus.dto.master.ProductSubSchemeCreateDto;
import com.zdatai.finverus.dto.master.ProductSubSchemeDto;
import com.zdatai.finverus.dto.master.ProductSubSchemeUpdateDto;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSubSchemeService {
    /**
     * Creates a new Product Sub-Scheme.
     *
     * @param createDto The DTO containing the details for the new Product Sub-Scheme
     * @return ApiResponse containing a success message or error details
     */
    ApiResponse<String> create(ProductSubSchemeCreateDto createDto);

    /**
     * Updates an existing Product Sub-Scheme.
     *
     * @param schemeId The ID of the Product Sub-Scheme to update
     * @param updateDto The DTO containing the updated details
     * @return ApiResponse containing a success message or error details
     */
    ApiResponse<String> update(Long schemeId, ProductSubSchemeUpdateDto updateDto);

    /**
     * Retrieves a Product Sub-Scheme by its ID.
     *
     * @param id The ID of the Product Sub-Scheme to retrieve
     * @return ApiResponse containing the Product Sub-Scheme details or error message
     */
    ApiResponse<ProductSubSchemeDto> getById(Long id);

    /**
     * Retrieves a paginated list of all Product Sub-Schemes.
     *
     * @param pageable The pagination information
     * @return ApiResponse containing a paginated list of Product Sub-Schemes
     */
    ApiResponse<Page<ProductSubSchemeDto>> getAllProductSubSchemes(Pageable pageable);

    /**
     * Retrieves a paginated list of Product Sub-Schemes filtered by status.
     *
     * @param status The status to filter by (ACTIVE or INACTIVE)
     * @param pageable The pagination information
     * @return ApiResponse containing a paginated list of filtered Product Sub-Schemes
     */
    ApiResponse<Page<ProductSubSchemeDto>> getAllByStatus(AuditModifyUser.Status status, Pageable pageable);
}
