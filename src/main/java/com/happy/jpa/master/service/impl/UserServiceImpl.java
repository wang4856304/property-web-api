package com.happy.jpa.master.service.impl;

import com.happy.jpa.master.entity.User;
import com.happy.jpa.master.dao.BaseQueryBySqlDao;
import com.happy.jpa.master.repo.UserRepository;
import com.happy.jpa.master.service.UserService;
import com.happy.jpa.utils.JPADynamicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jun.wang
 * @title: UserServiceImpl
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/3/2814:54
 */

@Service("jpaUserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BaseQueryBySqlDao baseQueryBySqlDao;

    //@Autowired
    //private CommonRepository<User> commonRepository;

    @Autowired
    @Qualifier("entityManagerPrimary")
    private EntityManager entityManager;

    @Override
    public List<User> findByCondition(Long id, String nickName, String email) {
        User user = new User();
        user.setId(id);
        user.setNickName(nickName);
        user.setEmail(email);
        Specification<User> specification = JPADynamicUtil.getSpecification(user);
        //List<User> users = repository.findAll(specification);
        String sql = "select * from user where id=?";
        List<Object> params = new ArrayList<>();
        params.add(1);
        BaseQueryBySqlDao.emThreadLocal.set(entityManager);
        User user1 = baseQueryBySqlDao.queryOne(sql, params, User.class);
        List<User> userList = baseQueryBySqlDao.queryList(sql, params, User.class);

        User userX = new User();
        userX.setNickName("王军");
        userX.setEmail("15289288565@163.com");
        return userList;
    }
}
