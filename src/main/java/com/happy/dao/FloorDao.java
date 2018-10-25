package com.happy.dao;

import com.happy.entity.po.FloorPo;

import java.util.List;

/**
 * @author wangjun
 * @Title: FloorDao
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/11 20:47
 */
public interface FloorDao {
    List<FloorPo> getFloorListByCommunityId(String communityId);
    FloorPo getFloorById(String floorId);
}
