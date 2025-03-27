package com.zdatai.finverus.controller.api.master;

import com.zdatai.finverus.constant.AppConstant;
import com.zdatai.finverus.model.master.Make;
import com.zdatai.finverus.model.master.Model;
import com.zdatai.finverus.request.application.master.MakeRequest;
import com.zdatai.finverus.request.application.master.ModelRequest;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.response.master.MakeResponse;
import com.zdatai.finverus.response.master.ModelPageResponse;
import com.zdatai.finverus.response.master.ModelResponse;
import com.zdatai.finverus.service.master.MakeService;
import com.zdatai.finverus.service.master.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/${version}/public/model")
public class ModelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelController.class);
    private final ModelService modelService;

    @Operation(summary = "Save Model Master Data", tags = {"Master Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully Save Model",
                    content = @Content(schema = @Schema(implementation = Model.class)))})
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<String>> save(@RequestBody @Valid final ModelRequest modelRequest) {
        LOGGER.info("Received request for Save Model");

        modelService.saveModel(modelRequest);

        LOGGER.info("Saved model successfully");
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "Get model using model_id", tags = {"Master Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved Model",
                    content = @Content(schema = @Schema(implementation = ModelResponse.class)))})
    @GetMapping(value = "/get/{modelId}")
    public ResponseEntity<ModelResponse> getById(
            @PathVariable Long modelId) {
        return ResponseEntity.ok(modelService.getModelById(modelId));
    }

    @Operation(summary = "Update model", tags = {"Master Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully updated model",
                    content = @Content(schema = @Schema(implementation = ModelResponse.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<ModelResponse> UpdateModel(@PathVariable final Long id, @RequestBody @Valid final ModelRequest modelRequest) {
        LOGGER.info("Received request to update model");

        final ModelResponse modelResponse = modelService.updateModel(id, modelRequest);

        LOGGER.info("Retrieved successfully updated model: {}", modelResponse);
        return ResponseEntity.ok(modelResponse);
    }

    @Operation(summary = "Get all Models", tags = {"Master Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved All Models",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ModelResponse.class))))})
    @GetMapping("/getAll")
    public ResponseEntity<ModelPageResponse> getAllModels(
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_NO) final int page,
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_SIZE) final int size) {
        LOGGER.info("Received request to get all Models");

        final ModelPageResponse modelPageResponse = modelService.getModels(page, size);

        LOGGER.info("Retrieved models successfully: {}", modelPageResponse);
        return ResponseEntity.ok(modelPageResponse);
    }
}
