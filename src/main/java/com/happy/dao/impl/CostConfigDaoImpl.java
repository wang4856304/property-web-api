package com.happy.dao.impl;

import com.happy.dao.BaseDao;
import com.happy.dao.CostConfigDao;
import com.happy.entity.bo.CostConfigModeBo;
import com.happy.entity.po.CostConfigPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangjun
 * @Title: CostConfigDaoImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/20 13:50
 */

@Repository
public class CostConfigDaoImpl extends BaseDao implements CostConfigDao {
    @Override
    public Integer updateProperty(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updateProperty", costConfigModeBo);
    }

    @Override
    public Integer updateWaste(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updateWaste", costConfigModeBo);
    }

    @Override
    public Integer updateEnergy(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updateEnergy", costConfigModeBo);
    }

    @Override
    public Integer updateElevator(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updateElevator", costConfigModeBo);
    }

    @Override
    public Integer updateWater(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updateWater", costConfigModeBo);
    }

    @Override
    public Integer updatePower(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updatePower", costConfigModeBo);
    }

    @Override
    public Integer updateHeat(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updateHeat", costConfigModeBo);
}

    @Override
    public Integer updatePropertyPrice(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updatePropertyPrice", costConfigModeBo);
    }

    @Override
    public Integer updateWastePrice(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updateWastePrice", costConfigModeBo);
    }

    @Override
    public Integer updateEnergyPrice(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updateEnergyPrice", costConfigModeBo);
    }

    @Override
    public Integer updateElevatorPrice(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updateElevatorPrice", costConfigModeBo);
    }

    @Override
    public Integer updateWaterPrice(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updateWaterPrice", costConfigModeBo);
    }

    @Override
    public Integer updatePowerPrice(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updatePowerPrice", costConfigModeBo);
    }

    @Override
    public Integer updateHeatPrice(CostConfigModeBo costConfigModeBo) {
        return masterSqlSessionTemplate.update("CostConfigMode.updateHeatPrice", costConfigModeBo);
    }

    @Override
    public List<CostConfigPo> getPropertyModeConfig() {
        return masterSqlSessionTemplate.selectList("CostConfigMode.getPropertyModeConfig");
    }

    @Override
    public List<CostConfigPo> getWasteModeConfig() {
        return masterSqlSessionTemplate.selectList("CostConfigMode.getWasteModeConfig");
    }

    @Override
    public List<CostConfigPo> getEnergyOvertModeConfig() {
        return masterSqlSessionTemplate.selectList("CostConfigMode.getEnergyOvertModeConfig");
    }

    @Override
    public List<CostConfigPo> getElevatorModeConfig() {
        return masterSqlSessionTemplate.selectList("CostConfigMode.getElevatorModeConfig");
    }

    @Override
    public List<CostConfigPo> getWaterModeConfig() {
        return masterSqlSessionTemplate.selectList("CostConfigMode.getWaterModeConfig");
    }

    @Override
    public List<CostConfigPo> getPowerModeConfig() {
        return masterSqlSessionTemplate.selectList("CostConfigMode.getPowerModeConfig");
    }

    @Override
    public List<CostConfigPo> getHeatModeConfig() {
        return masterSqlSessionTemplate.selectList("CostConfigMode.getHeatModeConfig");
    }
}
