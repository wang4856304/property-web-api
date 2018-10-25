package com.happy.dao;

import com.happy.entity.bo.UserBo;
import com.happy.entity.po.UserPo;

/**
 * @author wangjun
 * @Title: UserDao
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/17 16:40
 */
public interface UserDao {
    UserPo getUserByNameAndPwd(UserBo userBo);
    Integer updateUserLoginInfo(UserBo userBo);
    UserPo getUserById(String managerId);
    UserPo getUserInfoById(String managerId);
}
