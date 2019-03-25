package com.happy.dao.impl;

import com.happy.dao.BaseDao;
import com.happy.dao.CommonOrderDao;
import com.happy.entity.po.CommonOrderPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangjun
 * @Title: CommonOrderDaoImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/22 13:13
 */

@Repository
public class CommonOrderDaoImpl extends BaseDao implements CommonOrderDao {
    /*@Override
    public List<CommonOrderPo> getPropertyOrder(@Param("roomId") String roomId) {
        return masterSqlSessionTemplate.selectList("CommonOrder.getPropertyOrder", roomId);
    }

    @Override
    public List<CommonOrderPo> getPowerOrder(@Param("roomId")String roomId) {
        return masterSqlSessionTemplate.selectList("CommonOrder.getPowerOrder", roomId);
    }

    @Override
    public List<CommonOrderPo> getWaterOrder(@Param("roomId")String roomId) {
        return masterSqlSessionTemplate.selectList("CommonOrder.getWaterOrder", roomId);
    }

    @Override
    public List<CommonOrderPo> getParkSpaceOrder(@Param("roomId")String roomId) {
        return masterSqlSessionTemplate.selectList("CommonOrder.getParkSpaceOrder", roomId);
    }

    @Override
    public List<CommonOrderPo> getElevatorOrder(@Param("roomId")String roomId) {
        return masterSqlSessionTemplate.selectList("CommonOrder.getElevatorOrder", roomId);
    }

    @Override
    public List<CommonOrderPo> getEnergyOvertOrder(@Param("roomId")String roomId) {
        return masterSqlSessionTemplate.selectList("CommonOrder.getEnergyOvertOrder", roomId);
    }

    @Override
    public List<CommonOrderPo> getWasteOrder(@Param("roomId")String roomId) {
        return masterSqlSessionTemplate.selectList("CommonOrder.getWasteOrder", roomId);
    }*/
}
