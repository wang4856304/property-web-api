package com.happy.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjun
 * @Title: RoomBo
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/10 15:02
 */

@Data
@NoArgsConstructor
public class RoomBo {
    private String id;
    private String customerId;
    private String roomId;
    private String roomName;
    private String communityId;
    private Double price;
}
