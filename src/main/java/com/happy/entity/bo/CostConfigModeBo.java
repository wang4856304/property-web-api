package com.happy.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wangjun
 * @Title: CostConfigModeBo
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/20 13:36
 */

@Data
@NoArgsConstructor
public class CostConfigModeBo {
    private String chargeType;
    private String standard;
    private String communityId;
    private List<String> floorIdList;
    private String floorId;
    private String unitId;
    private List<String> unitIdList;

    private Double propertyPrice;
    private Double wastePrice;
    private Double energyPrice;
    private Double elevatorPrice;
    private Double waterPrice;
    private Double powerPrice;
    private Double heatPrice;
    private Integer elevatorStartLayer;//电梯起始楼层
    private Double elevatorIncrease;//电梯费层涨幅
}
