package com.happy.dao;

import com.happy.entity.bo.CostConfigModeBo;
import com.happy.entity.po.CostConfigPo;

import java.util.List;

/**
 * @author wangjun
 * @Title: CostConfigDao
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/20 13:47
 */
public interface CostConfigDao {
    Integer updateProperty(CostConfigModeBo costConfigModeBo);
    Integer updateWaste(CostConfigModeBo costConfigModeBo);
    Integer updateEnergy(CostConfigModeBo costConfigModeBo);
    Integer updateElevator(CostConfigModeBo costConfigModeBo);
    Integer updateWater(CostConfigModeBo costConfigModeBo);
    Integer updatePower(CostConfigModeBo costConfigModeBo);
    Integer updateHeat(CostConfigModeBo costConfigModeBo);

    Integer updatePropertyPrice(CostConfigModeBo costConfigModeBo);
    Integer updateWastePrice(CostConfigModeBo costConfigModeBo);
    Integer updateEnergyPrice(CostConfigModeBo costConfigModeBo);
    Integer updateElevatorPrice(CostConfigModeBo costConfigModeBo);
    Integer updateWaterPrice(CostConfigModeBo costConfigModeBo);
    Integer updatePowerPrice(CostConfigModeBo costConfigModeBo);
    Integer updateHeatPrice(CostConfigModeBo costConfigModeBo);
    List<CostConfigPo> getPropertyModeConfig();
    List<CostConfigPo> getWasteModeConfig();
    List<CostConfigPo> getEnergyOvertModeConfig();
    List<CostConfigPo> getElevatorModeConfig();
    List<CostConfigPo> getWaterModeConfig();
    List<CostConfigPo> getPowerModeConfig();
    List<CostConfigPo> getHeatModeConfig();
}
