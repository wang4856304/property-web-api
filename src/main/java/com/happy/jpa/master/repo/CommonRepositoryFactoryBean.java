package com.happy.jpa.master.repo;

import com.happy.jpa.master.repo.impl.CommonRepositoryImpl;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author jun.wang
 * @title: CommonRepositoryFactoryBean
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/4/1 14:46
 */
public class CommonRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID> {

    public CommonRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {

        return new MyRepositoryFactory(em);

    }

    private static class MyRepositoryFactory<S, ID extends Serializable> extends JpaRepositoryFactory {
        private final EntityManager em;
        public MyRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

        protected Object getTargetRepository(RepositoryInformation information) {
            return new CommonRepositoryImpl<S, ID>((Class<S>)information.getDomainType(), em);
        }

        protected Class getRepositoryBaseClass(RepositoryMetadata metadata) {

            return CommonRepositoryImpl.class;

        }
    }
}
