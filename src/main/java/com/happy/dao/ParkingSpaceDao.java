package com.happy.dao;

import com.happy.entity.bo.ParkingSpaceBo;
import com.happy.entity.bo.CostConfigBo;

import java.util.List;

/**
 * @author wangjun
 * @Title: ParkingSpaceDao
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 20:26
 */
public interface ParkingSpaceDao {
    Integer insertParkingSpace(ParkingSpaceBo parkingSpaceBo);
    Integer updateParkingSpacePrice(List<CostConfigBo> costConfigBoList);
}
