package com.happy.dao.impl;

import com.happy.dao.BaseDao;
import com.happy.dao.UserDao;
import com.happy.entity.bo.UserBo;
import com.happy.entity.po.UserPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wangjun
 * @Title: UserDaoImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/17 17:04
 */

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public UserPo getUserByNameAndPwd(UserBo userBo) {
        return masterSqlSessionTemplate.selectOne("User.getUserByNameAndPwd", userBo);
    }

    @Override
    public Integer updateUserLoginInfo(UserBo userBo) {
        return masterSqlSessionTemplate.update("User.updateUserLoginInfo", userBo);
    }

    @Override
    public UserPo getUserById(@Param("managerId") String managerId) {
        return masterSqlSessionTemplate.selectOne("User.getUserById", managerId);
    }

    @Override
    public UserPo getUserInfoById(String managerId) {
        return masterSqlSessionTemplate.selectOne("User.getUserInfoById", managerId);
    }
}
