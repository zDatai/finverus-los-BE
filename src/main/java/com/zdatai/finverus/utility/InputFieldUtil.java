package com.zdatai.finverus.utility;

import com.zdatai.finverus.dto.InputField;

public class InputFieldUtil {
    public static <T> String getValue(InputField<T> inputField) {
        return inputField.getValue();
    }
}
