package com.happy.dao.impl;

import com.happy.dao.BaseDao;
import com.happy.dao.MenuDao;
import com.happy.entity.po.MenuPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangjun
 * @Title: MenuDaoImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/17 21:14
 */

@Repository
public class MenuDaoImpl extends BaseDao implements MenuDao {
    @Override
    public List<MenuPo> getMenuListById(@Param("managerId") String managerId) {
        return masterSqlSessionTemplate.selectList("Menu.getMenuListById", managerId);
    }

    @Override
    public List<MenuPo> getAllMenuList() {
        return masterSqlSessionTemplate.selectList("Menu.getAllMenuList");
    }
}
