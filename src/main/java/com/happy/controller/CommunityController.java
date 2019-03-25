package com.happy.controller;

import com.happy.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjun
 * @Title: CommunityController
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 10:16
 */

@RestController
@RequestMapping("/community")
public class CommunityController extends BaseController {

    @Autowired
    private CommunityService communityService;

    /**
     * 获取小区收费模式
     * @param communityId
     * @return
     */
    @RequestMapping("/getCommunityMode")
    public Object getCommunityMode(@RequestParam String communityId) {

        return buildResponse(communityService.getCommunityMode(communityId));
    }

    @RequestMapping("/getFloorList")
    public Object getFloorList(@RequestParam String communityId) {
        return buildResponse(communityService.getFloorList(communityId));
    }

    @RequestMapping("/getUnitList")
    public Object getUnitList(@RequestParam String floorId) {
        return buildResponse(communityService.getUnitList(floorId));
    }
}
