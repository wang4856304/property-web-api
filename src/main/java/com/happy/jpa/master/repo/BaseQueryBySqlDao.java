package com.happy.jpa.master.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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

    @Autowired
    @Qualifier("entityManagerPrimary")
    private EntityManager entityManager;

    public List queryOne(String sql, List<Object> params, Class clazz) {
        Query query = entityManager.createNativeQuery(sql, clazz);
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
    }
}
