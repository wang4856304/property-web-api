package com.happy.controller;

import com.happy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjun
 * @Title: MenuController
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/17 20:54
 */

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/getUserMenuList")
    public Object getUserMenuList(@RequestHeader String token) {
        return buildResponse(menuService.getUserMenuList(token));
    }
}
