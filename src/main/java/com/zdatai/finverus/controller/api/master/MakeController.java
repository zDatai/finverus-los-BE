package com.zdatai.finverus.controller.api.master;

import com.zdatai.finverus.constant.AppConstant;
import com.zdatai.finverus.model.master.Make;
import com.zdatai.finverus.request.application.master.MakeRequest;
import com.zdatai.finverus.response.ApiResponse;
import com.zdatai.finverus.response.master.MakePageResponse;
import com.zdatai.finverus.response.master.MakeResponse;
import com.zdatai.finverus.service.master.MakeService;
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

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/${version}/public/make")
public class MakeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MakeController.class);
    private final MakeService makeService;

    @Operation(summary = "Save Make Master Data", tags = {"Master Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully Save Make",
                    content = @Content(schema = @Schema(implementation = Make.class)))})
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<String>> save(@RequestBody @Valid final MakeRequest makeRequest) {
        LOGGER.info("Received request for Save Make");

        makeService.saveMake(makeRequest);

        LOGGER.info("Saved make successfully");
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "Get make using make_id", tags = {"Master Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved Make",
                    content = @Content(schema = @Schema(implementation = MakeResponse.class)))})
    @GetMapping(value = "/get/{makeId}")
    public ResponseEntity<MakeResponse> getById(
            @PathVariable Long makeId) {
        return ResponseEntity.ok(makeService.getMakeById(makeId));
    }

    @Operation(summary = "Update make", tags = {"Master Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully updated make",
                    content = @Content(schema = @Schema(implementation = MakeResponse.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<MakeResponse> UpdateMake(@PathVariable final Long id, @RequestBody @Valid final MakeRequest makeRequest) {
        LOGGER.info("Received request to update make");

        final MakeResponse makeResponse = makeService.updateMake(id, makeRequest);

        LOGGER.info("Retrieved successfully updated make: {}", makeResponse);
        return ResponseEntity.ok(makeResponse);
    }

    @Operation(summary = "Retrieve All Make", tags = {"Master Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved  All Make",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MakeResponse.class))))})
    @GetMapping(value = "/get/all")
    public ResponseEntity<MakePageResponse> getAllMake(
            @RequestParam(value = "pageNo", defaultValue = AppConstant.DEFAULT_PAGE_NO) final int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.DEFAULT_PAGE_SIZE) final int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "makeId") final String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstant.DEF_SORT_DIR_DESC) final String sortDir,
            @RequestParam Map<String, String> queryParams) {

        LOGGER.info("Received Request to fetch all Make with params: pageNo={}, pageSize={}, sortBy={}, sortDir={}, queryParams={}");
        MakePageResponse response = makeService.getAllMake(pageNo, pageSize, sortBy, sortDir, queryParams);
        LOGGER.info("Fetched all Make successfully. Total Elements: {}, Total Pages: {}");
        return ResponseEntity.ok(response);
    }

}
