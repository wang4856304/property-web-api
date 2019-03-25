package com.happy.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wangjun
 * @Title: CostConfigDto
 * @ProjectName newHappy
 * @Description: 费用配置管理
 * @date 2018/10/19 18:17
 */

@Data
@NoArgsConstructor
public class CostConfigDto {

    private Double propertyPrice;//物业费
    private Double wastePrice;//卫生垃圾管理费
    private Double energyOvertPrice;//能源分摊费
    private Double elevatorPrice;//电梯费
    private Integer elevatorStartLayer;//电梯起始楼层
    private Double elevatorIncrease;//电梯费层涨幅
    private Double waterPrice;//水费
    private Double powerPrice;//电费
    private Double heatPrice;//暖气费
    private Double heatFlowPrice;//暖气流量费
    private Double heatPercent;//暖气费基础比例
    private Double heatTime;//供暖时长

    private Double rentTopManagerPrice;//地上车位租售管理费
    private Double rentMidManagerPrice;//空中车位租售管理费
    private Double rentBottomManagerPrice;//地下车位租售管理费

    private Double rentTopRentPrice;//地上车位租售租金
    private Double rentMidRentPrice;//空中车位租售租金
    private Double rentBottomRentPrice;//地下车位租售租金

    private Double saleTopManagerPrice;//地上车位已售管理费
    private Double saleMidManagerPrice;//空中车位已售管理费
    private Double saleBottomManagerPrice;//地下车位已售管理费

    private Double freeTopManagerPrice;//地上车位未租未售管理费
    private Double freeMidManagerPrice;//空中车位未租未售管理费
    private Double freeBottomManagerPrice;//地下车位未租未售管理费

    private String floorIds;

    private String communityId;
    private String floorId;
    private String unitIds;
    private String floorNames;
    private String unitNames;

    private String propertyType;//1:按面积/时间(元/平米/月)
    private String propertyConfigName;

    private String wasteType;//0：不收此项费,1:按面积/时间(元/平米/月),2:按人/时间(元/人/月),3:按户/时间(元/月)
    private String wasteConfigName;

    private String energyType;//0：不收此项费,1:按面积/时间(元/平米/月),2:按人/时间(元/人/月),3:按户/时间(元/月)
    private String energyConfigName;

    private String elevatorType;//0：不收此项费,1:阶梯式收费按面积/时间(元/平米/月),2:统一式收费按面积/时间(元/平米/月)
    private String elevatorConfigName;

    private String waterType;//0：不收此项费,1:统一收费/时间(元/月),2:抄表按使用量收费(元/吨),3:充卡
    private String waterConfigName;

    private String powerType;//0：不收此项费,1:统一收费/时间(元/月),2:抄表按使用量收费(元/度),3:充卡
    private String powerConfigName;

    private String heatType;//0：不收此项费,1:抄表按使用量收费+基础费(面积*市政*时间)(元/平/月 + 元/G焦),2:按面积/时间+基础费(面积*市政*时间)(元/平/月)
    private String heatConfigName;
}
