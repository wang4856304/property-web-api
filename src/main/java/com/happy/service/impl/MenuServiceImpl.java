package com.happy.service.impl;

import com.happy.dao.MenuDao;
import com.happy.dao.UserDao;
import com.happy.entity.Response;
import com.happy.entity.po.MenuPo;
import com.happy.entity.po.UserPo;
import com.happy.enums.ErrorEnum;
import com.happy.service.BaseService;
import com.happy.service.MenuService;
import com.happy.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wangjun
 * @Title: MenuServiceImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/17 21:18
 */

@Service
public class MenuServiceImpl extends BaseService implements MenuService {

    private static final String ADMIN="admin";

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MenuDao menuDao;
    @Override
    public Response getUserMenuList(String token) {
        String managerId = redisService.get(token);

        List<MenuPo> menuPos = null;
        UserPo userPo = userDao.getUserById(managerId);
        if (ADMIN.equals(userPo.getUserName())) {
            menuPos = menuDao.getAllMenuList();
        }
        else {
            menuPos = menuDao.getMenuListById(managerId);
        }
        return buildSuccesResponse(menuPos);
    }
}
