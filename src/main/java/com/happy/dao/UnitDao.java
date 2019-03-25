package com.happy.dao;

import com.happy.entity.po.UnitPo;

import java.util.List;

/**
 * @author wangjun
 * @Title: UnitDao
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/11 20:54
 */
public interface UnitDao {
    List<UnitPo> getUnitListByFloorId(String floorId);
}
