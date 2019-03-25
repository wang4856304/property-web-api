package com.happy.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author wangjun
 * @Title: CostConfigBo
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/19 18:21
 */

@Data
@NoArgsConstructor
public class CostConfigBo {

    private Integer useType;//1:租赁,2:出售,3：未租未售
    private Integer isFree;//是否空置,1:空置,0:非空置
    private Integer isSale;//是否售出,1:售出,0:非售出

    private Integer parkType;//1:地下车位,2:地上车位,3:空中车位
    private Double mangerPrice;
    private Double rentPrice;
    private String communityId;
}
