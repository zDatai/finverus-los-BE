package com.zdatai.finverus.controller.api.master;

import com.zdatai.finverus.service.master.InsuranceInstituteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/${version}/public/assetcategory")
public class InsuranceInstituteController {

    private final InsuranceInstituteService insuranceInstituteService;
}
