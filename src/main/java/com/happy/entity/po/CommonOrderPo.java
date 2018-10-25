package com.happy.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjun
 * @Title: CommonOrderPo
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/22 12:26
 */

@Data
@NoArgsConstructor
public class CommonOrderPo {
    private String roomId;

    private String ownerName;

    private String price;

    private Double acreage;

    private String ownerTel;

    private String communityId;

    private String communityName;

    private String province;

    private String city;

    private String floorName;

    private String unitName;

    private String roomName;

    private String propertyCompanyId;

    private String propertyCompanyName;

    private String propertyCompanyTel;

    private Boolean isDefault;

    private String waterUseAmount;

    private String heaterUseAmount;

    private String powerUseAmount;
}
