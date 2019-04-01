package com.happy.jpa.master.dao;

import com.happy.jpa.master.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author jun.wang
 * @title: QueryBySqlDao
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/3/28 16:52
 */

@Component
public class BaseQueryBySqlDao {

    public static ThreadLocal<EntityManager> emThreadLocal = new ThreadLocal<>();

    public<T> T queryOne(String sql, List<Object> params, Class clazz) {
        EntityManager entityManager = emThreadLocal.get();
        Query query = entityManager.createNativeQuery(sql, clazz);
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i+1, params.get(i));
        }
        List resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            return (T)resultList.get(0);
        }
        return null;
    }

    public<T> List<T> queryList(String sql, List<Object> params, Class clazz) {
        EntityManager entityManager = emThreadLocal.get();
        Query query = entityManager.createNativeQuery(sql, clazz);
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i+1, params.get(i));
        }
        List resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            return resultList;
        }
        return Collections.emptyList();
    }
}
