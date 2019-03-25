package com.happy.service;

import com.happy.entity.Response;

/**
 * @author wangjun
 * @Title: MenuService
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/17 21:18
 */
public interface MenuService {

    Response getUserMenuList(String token);
}
