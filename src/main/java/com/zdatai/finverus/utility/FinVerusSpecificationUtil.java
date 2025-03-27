package com.zdatai.finverus.utility;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FinVerusSpecificationUtil<T> {
    private FinVerusSpecificationUtil() {}

    public static <T> Specification<T> getSpecification(Map<String, String> filters) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            for (Map.Entry<String, String> entry : filters.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                Path<Object> path;
                if (key.contains(".")) {
                    String[] keys = key.split("\\.");
                    path = (Path<Object>) root;
                    for (int i = 0; i < keys.length - 1; i++) {
                        path = ((From<?, ?>) path).join(keys[i], JoinType.LEFT);
                    }
                    path = path.get(keys[keys.length - 1]);
                } else {
                    path = root.get(key);
                }

                if (Number.class.isAssignableFrom(path.getJavaType())) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder
                            .equal(path, Integer.parseInt(value)));
                } else {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder
                            .like(path.as(String.class), "%" + value + "%"));
                }
            }

            return predicate;
        };
    }

    public static <T> Specification<T> getSpecification(Map<String, String> searchParams, Class<T> entityClass) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (Map.Entry<String, String> entry : searchParams.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                if (value == null || value.trim().isEmpty()) {
                    continue;
                }

                if (key.contains(".")) {
                    String[] parts = key.split("\\.");
                    String relation = parts[0];
                    String field = parts[1];

                    Join<T, ?> join = root.join(relation, JoinType.INNER);
                    predicates.add(criteriaBuilder.equal(join.get(field), convertValue(entityClass, field, value)));
                } else {
                    predicates.add(criteriaBuilder.equal(root.get(key), convertValue(entityClass, key, value)));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static Object convertValue(Class<?> entityClass, String fieldName, String value) {
        try {
            Field field = entityClass.getDeclaredField(fieldName);
            Class<?> fieldType = field.getType();

            if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                return Long.parseLong(value);
            } else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                return Integer.parseInt(value);
            } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                return Double.parseDouble(value);
            } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                return Boolean.parseBoolean(value);
            } else {
                return value;
            }
        } catch (NoSuchFieldException e) {
            return value;
        }
    }
}
