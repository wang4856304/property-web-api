package com.happy.dao.impl;

import com.happy.dao.BaseDao;
import com.happy.dao.CreatePreOrderDao;
import com.happy.entity.bo.OrderBo;
import com.happy.entity.bo.RoomBo;
import com.happy.entity.po.RoomCostConfigPo;
import com.happy.entity.po.RoomPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CreatePreOrderDaoImpl extends BaseDao implements CreatePreOrderDao {

    @Override
    public List<RoomPo> getRoomListByCommunity(@Param("communityId")String communityId) {
        return masterSqlSessionTemplate.selectList("PreOrder.getRoomListByCommunity", communityId);
    }

    @Override
    public List<RoomCostConfigPo> getRoomWaterConfigList(List<String> roomIdList) {
        return masterSqlSessionTemplate.selectList("PreOrder.getRoomWaterConfigList", roomIdList);
    }

    @Override
    public List<RoomCostConfigPo> getRoomWasteConfigList(List<String> roomIdList) {
        return masterSqlSessionTemplate.selectList("PreOrder.getRoomWasteConfigList", roomIdList);
    }

    @Override
    public List<RoomCostConfigPo> getRoomPowerConfigList(List<String> roomIdList) {
        return masterSqlSessionTemplate.selectList("PreOrder.getRoomPowerConfigList", roomIdList);
    }

    @Override
    public Integer createWaterOrder(List<OrderBo> orderBoList) {
        return masterSqlSessionTemplate.insert("PreOrder.createWaterOrder", orderBoList);
    }

    @Override
    public Integer createWasteOrder(List<OrderBo> orderBoList) {
        return masterSqlSessionTemplate.insert("PreOrder.createWasteOrder", orderBoList);
    }

    @Override
    public Integer createPowerOrder(List<OrderBo> orderBoList) {
        return masterSqlSessionTemplate.insert("PreOrder.createPowerOrder", orderBoList);
    }


}
