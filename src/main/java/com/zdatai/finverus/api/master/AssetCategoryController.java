package com.zdatai.finverus.api.master;

import com.zdatai.finverus.service.master.AssetCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/${version}/public/asset-category")
public class AssetCategoryController {

    private final AssetCategoryService assetCategoryService;

}
