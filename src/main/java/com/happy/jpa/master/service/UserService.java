package com.happy.jpa.master.service;

import com.happy.jpa.master.entity.User;

import java.util.List;

/**
 * @author jun.wang
 * @title: UserService
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/3/2814:54
 */
public interface UserService {
    List<User> findByCondition(Long id, String nickName, String email);
}
