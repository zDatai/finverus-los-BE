package com.zdatai.finverus.utility;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class FinVerusSpecificationUtil<T> {
    private FinVerusSpecificationUtil() {}

    public static <T> Specification<T> getSpecification(Map<String, String> filters) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            for (Map.Entry<String, String> entry : filters.entrySet()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(
                                root.get(entry.getKey()), "%" + entry.getValue() + "%")
                );
            }
            return predicate;
        };
    }
}
