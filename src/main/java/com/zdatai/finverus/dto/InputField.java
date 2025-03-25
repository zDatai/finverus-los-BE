package com.zdatai.finverus.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputField<T> {
    private String value;

    @JsonIgnore
    private T typedValue;
}
