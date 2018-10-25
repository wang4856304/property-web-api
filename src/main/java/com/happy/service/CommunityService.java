package com.happy.service;

import com.happy.entity.Response;

/**
 * @author wangjun
 * @Title: CommunityService
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 10:18
 */
public interface CommunityService {

    Response getCommunityMode(String communityId);
    Response getFloorList(String communityId);
    Response getUnitList(String floorId);
}
