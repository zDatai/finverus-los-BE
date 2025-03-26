package com.zdatai.finverus.dto.master;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ProductSchemaDto{
    private Long productId;
    private String productName;
    private String productType;
    private String accountNoPrefix;
    private String activityType;
    private String status;
    private Integer version;



}
