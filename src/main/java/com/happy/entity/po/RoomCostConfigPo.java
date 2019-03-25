package com.happy.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author wangjun
 * @Title: RoomCostConfigPo
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/10 18:13
 */

@Data
@NoArgsConstructor
public class RoomCostConfigPo {
    private String roomId;
    private BigDecimal price;
}
