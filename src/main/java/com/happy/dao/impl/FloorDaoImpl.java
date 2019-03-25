package com.happy.dao.impl;

import com.happy.dao.BaseDao;
import com.happy.dao.FloorDao;
import com.happy.entity.po.FloorPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangjun
 * @Title: FloorDaoImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/11 20:50
 */

@Repository
public class FloorDaoImpl extends BaseDao implements FloorDao {
    @Override
    public List<FloorPo> getFloorListByCommunityId(@Param("communityId") String communityId) {
        return masterSqlSessionTemplate.selectList("Floor.getFloorListByCommunityId", communityId);
    }

    @Override
    public FloorPo getFloorById(@Param("floorId") String floorId) {
        return masterSqlSessionTemplate.selectOne("Floor.getFloorById", floorId);
    }
}
