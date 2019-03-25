package com.happy.dao;

import com.happy.entity.bo.OrderBo;
import com.happy.entity.bo.RoomBo;
import com.happy.entity.po.RoomCostConfigPo;
import com.happy.entity.po.RoomPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CreatePreOrderDao {
    List<RoomPo> getRoomListByCommunity(@Param("communityId") String communityId);
    List<RoomCostConfigPo> getRoomWaterConfigList(List<String> roomIdList);
    List<RoomCostConfigPo> getRoomWasteConfigList(List<String> roomIdList);
    List<RoomCostConfigPo> getRoomPowerConfigList(List<String> roomIdList);
    Integer createWaterOrder(List<OrderBo> orderBoList);
    Integer createWasteOrder(List<OrderBo> orderBoList);
    Integer createPowerOrder(List<OrderBo> orderBoList);
}
