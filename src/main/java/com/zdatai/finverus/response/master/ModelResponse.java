package com.zdatai.finverus.response.master;

import com.zdatai.finverus.model.master.Make;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelResponse {
    private Long modelId;
    private String model;
    private Long make;
    private Long assetCategory;
}
