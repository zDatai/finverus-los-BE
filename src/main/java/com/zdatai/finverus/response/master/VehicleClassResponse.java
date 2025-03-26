package com.zdatai.finverus.response.master;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VehicleClassResponse {
    private Long vehicalClassId;
    private String vehicalClass;
    private BigDecimal ltvRatio;


}
