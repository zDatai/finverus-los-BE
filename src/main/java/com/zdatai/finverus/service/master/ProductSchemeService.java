package com.zdatai.finverus.service.master;

import com.zdatai.finverus.dto.master.ProductSchemaUpdateDto;
import com.zdatai.finverus.dto.master.ProductSchemeCreateDto;
import com.zdatai.finverus.response.ApiResponse;
import jakarta.validation.Valid;


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

    ApiResponse<String> update(Long productId, @Valid ProductSchemaUpdateDto updateDto);
}
