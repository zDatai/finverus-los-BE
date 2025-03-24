package com.zdatai.finverus.utility;

import com.zdatai.finverus.dto.InputField;

/**
 * author: Ishara date: 2/28/2025 12:39 PM ticket no: FV-73 purpose:Personal information crud
 * operation.
 */
public class CreateFieldUtil {
    public static <T> InputField<T> createField(Class<T> fieldType, Object value, String description) {
        InputField<T> field = new InputField<>();
        field.setDescription(description);

        if (fieldType.isInstance(value)) {
            //field.setValue(fieldType.cast(value));
        } else {
            throw new IllegalArgumentException("Invalid value type: Expected " + fieldType.getSimpleName());
        }

        return field;
    }
}
