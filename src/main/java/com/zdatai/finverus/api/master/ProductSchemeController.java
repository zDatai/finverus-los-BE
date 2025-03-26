package com.zdatai.finverus.api.master;

import com.zdatai.finverus.dto.master.ProductSchemaUpdateDto;
import com.zdatai.finverus.dto.master.ProductSchemeCreateDto;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.service.master.ProductSchemeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/${version}/public/product-scheme")
@RequiredArgsConstructor
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
    public ResponseEntity<ApiResponse<String>> create(@RequestBody @Valid
                                                          final ProductSchemeCreateDto newProductScheme) {
        ApiResponse<String> response = service.create(newProductScheme);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update/{productId}")
    public ResponseEntity<ApiResponse<String>> update(@PathVariable final Long productId, @RequestBody @Valid
                                                          final ProductSchemaUpdateDto updateDto) {
        ApiResponse<String> response = service.update(productId, updateDto);
        return ResponseEntity.ok(response);
    }

}
