package com.happy.jpa.master.repo.impl;

import com.happy.jpa.master.repo.CommonRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * @author jun.wang
 * @title: CommonRepositoryImpl
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/4/1 14:40
 */
public class CommonRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CommonRepository<T, ID> {

    public CommonRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public T queryOne(String sql, List params, Class clazz) {
        return null;
    }

    @Override
    public List<T> queryList(String sql, List params, Class clazz) {
        return null;
    }
}
