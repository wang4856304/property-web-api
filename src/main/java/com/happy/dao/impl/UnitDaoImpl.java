package com.happy.dao.impl;

import com.happy.dao.BaseDao;
import com.happy.dao.UnitDao;
import com.happy.entity.po.UnitPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangjun
 * @Title: UnitDaoImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/11 20:57
 */

@Repository
public class UnitDaoImpl extends BaseDao implements UnitDao {
    @Override
    public List<UnitPo> getUnitListByFloorId(@Param("floorId") String floorId) {
        return masterSqlSessionTemplate.selectList("Unit.getUnitListByFloorId", floorId);
    }
}
