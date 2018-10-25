package com.happy.dao.impl;

import com.happy.dao.BaseDao;
import com.happy.dao.ParkingSpaceDao;
import com.happy.entity.bo.ParkingSpaceBo;
import com.happy.entity.bo.CostConfigBo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangjun
 * @Title: ParkingSpaceDaoImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 20:27
 */

@Repository
public class ParkingSpaceDaoImpl extends BaseDao implements ParkingSpaceDao {
    @Override
    public Integer insertParkingSpace(ParkingSpaceBo parkingSpaceBo) {
        return masterSqlSessionTemplate.insert("ParkingSpace.insertParkingSpace", parkingSpaceBo);
    }

    @Override
    public Integer updateParkingSpacePrice(List<CostConfigBo> costConfigBoList) {
        return masterSqlSessionTemplate.update("ParkingSpace.updateParkingSpacePrice", costConfigBoList);
    }
}
