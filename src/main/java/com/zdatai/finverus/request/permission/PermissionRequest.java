package com.zdatai.finverus.request.permission;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequest {
    private int parentId;
    private int active;
    private int productId;
    @NotBlank(message = "Description required")
    private String description;
    @NotBlank(message = "Code required")
    private String code;
    private List<Long> eventIdList;
}
