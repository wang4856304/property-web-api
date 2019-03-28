package com.happy.jpa.utils;

import com.happy.exception.BusinessException;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author jun.wang
 * @title: JPADynamicUtil
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/3/2815:30
 */
public class JPADynamicUtil {

    public static<T> Specification<T> getSpecification(T var) {
        if (var == null) {
            return null;
        }
        Map<String, Object> fieldMap = new HashMap<>();
        try {
            Class clazz = var.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field: fields) {
                field.setAccessible(true);
                Object value = field.get(var);
                if (value == null) {
                    continue;
                }
                if (value instanceof String) {
                    if (((String) value).length() != 0) {
                        fieldMap.put(field.getName(), value);
                    }
                }
                else {
                    fieldMap.put(field.getName(), value);
                }
            }
            Set<Map.Entry<String, Object>> set = fieldMap.entrySet();
            Specification<T> specification = (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                for (Map.Entry<String, Object> entry: set) {
                    String name = entry.getKey();
                    Object value = entry.getValue();
                    predicates.add(criteriaBuilder.equal(root.<String>get(name), value));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
            return specification;
        }
        catch (Exception e) {
            throw new BusinessException("get jpa dynamic specification error", e);
        }
    }
}
