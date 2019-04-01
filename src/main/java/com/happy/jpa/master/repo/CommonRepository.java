package com.happy.jpa.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author jun.wang
 * @title: CommonRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/3/29 10:41
 */

@NoRepositoryBean
public  interface CommonRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    T queryOne(String sql, List<Object> params, Class clazz);
    List<T> queryList(String sql, List<Object> params, Class clazz);
}
