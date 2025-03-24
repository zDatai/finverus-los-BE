package com.zdatai.finverus.utility;

import com.zdatai.finverus.exception.FinVerusException;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CopyPropertyUtil {
    private CopyPropertyUtil() {}

    public static <S, T> T copyProperties(S source, Class<T> targetClass) {
        try {
            T targetInstance = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, targetInstance);

            return targetInstance;
        } catch (Exception e) {
            throw new FinVerusException(String.format("Error copying properties %s", e));
        }
    }

    public static <S, T> List<T> copyList(List<S> sourceList, Class<T> targetClass) {
        return sourceList.stream()
                .map(source -> copyProperties(source, targetClass))
                .collect(Collectors.toList());
    }

    public static <S, T> T copyProperties(S source, Class<T> targetClass, String[] ignoreProperties) {
        try {
            T targetInstance = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, targetInstance, ignoreProperties);

            return targetInstance;
        } catch (Exception e) {
            throw new FinVerusException(String.format("Error copying properties %s", e));
        }
    }
}
