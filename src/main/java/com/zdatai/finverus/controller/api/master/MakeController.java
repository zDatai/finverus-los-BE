package com.zdatai.finverus.controller.api.master;

import com.zdatai.finverus.constant.AppConstant;
import com.zdatai.finverus.response.master.MakePageResponse;
import com.zdatai.finverus.response.master.MakeResponse;
import com.zdatai.finverus.service.master.MakeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/${version}/public/make")
public class MakeController {

    private final MakeService makeService;

    @Operation(summary = "Retrieve  All Make", tags = {"Master Data"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved  All Make",
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
