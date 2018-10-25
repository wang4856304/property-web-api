package com.happy.dao;

import com.happy.entity.po.MenuPo;

import java.util.List;

/**
 * @author wangjun
 * @Title: MenuDao
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/17 21:14
 */
public interface MenuDao {
    List<MenuPo> getMenuListById(String managerId);
    List<MenuPo> getAllMenuList();
}
