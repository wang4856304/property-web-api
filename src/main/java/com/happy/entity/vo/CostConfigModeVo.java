package com.happy.entity.vo;

import com.happy.entity.po.CostConfigPo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wangjun
 * @Title: CostConfigModeVo
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/24 18:36
 */

@Data
@NoArgsConstructor
public class CostConfigModeVo {
    private List<CostConfigPo> propertyCostConfigList;
    private List<CostConfigPo> wasteCostConfigList;
    private List<CostConfigPo> energyOvertCostConfigList;
    private List<CostConfigPo> elevatorCostConfigList;
    private List<CostConfigPo> waterCostConfigList;
    private List<CostConfigPo> powerCostConfigList;
    private List<CostConfigPo> heatCostConfigList;
}
