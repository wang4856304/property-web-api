package com.happy.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjun
 * @Title: CostConfigModeDto
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/20 13:09
 */

@Data
@NoArgsConstructor
public class CostConfigModeDto {

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

    private String communityId;
}
