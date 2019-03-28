package com.happy.jpa.master.service.impl;

import com.happy.jpa.master.entity.User;
import com.happy.jpa.master.repo.BaseQueryBySqlDao;
import com.happy.jpa.master.repo.UserRepository;
import com.happy.jpa.master.service.UserService;
import com.happy.jpa.utils.JPADynamicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

    @Override
    public List<User> findByCondition(Long id, String nickName, String email) {
        User user = new User();
        user.setId(id);
        user.setNickName(nickName);
        user.setEmail(email);
        Specification<User> specification = JPADynamicUtil.getSpecification(user);
        List<User> users = userRepository.findAll(specification);
        String sql = "select * from user where id=?";
        List<Object> params = new ArrayList<>();
        params.add(1);
        List userList = baseQueryBySqlDao.queryOne(sql, params, User.class);
        return users;
    }
}
