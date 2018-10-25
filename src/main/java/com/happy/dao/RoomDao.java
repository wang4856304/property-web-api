package com.happy.dao;

import com.happy.entity.bo.RoomBo;
import com.happy.entity.po.RoomPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangjun
 * @Title: RoomDao
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/10 14:41
 */
public interface RoomDao {

    String getDefaultBindRoom(@Param("roomId")String customerId);
    List<String> getRoomList(@Param("roomId")String customerId);
    RoomPo getRoomById(@Param("roomId") String roomId);
    Integer updateDefaultBindRoom(RoomBo roomBo);
    Integer updateOtherRoomDefault(RoomBo roomBo);
    Integer bindRoom(RoomBo roomBo);
    Integer deleteBindRoom(RoomBo roomBo);

    List<RoomPo> getRoomProperty(RoomBo roomBo);
    Integer updateRoomPropertyPrice(List<RoomBo> roomBoList);
    List<RoomPo> getRoomPower(RoomBo roomBo);
    Integer updateRoomPowerPrice(List<RoomBo> roomBoList);
    List<RoomPo> getRoomWater(RoomBo roomBo);
    Integer updateRoomWaterPrice(List<RoomBo> roomBoList);
    List<RoomPo> getRoomElevator(RoomBo roomBo);
    Integer updateRoomElevatorPrice(List<RoomBo> roomBoList);
    List<RoomPo> getRoomEnergy(RoomBo roomBo);
    Integer updateRoomEnergyPrice(List<RoomBo> roomBoList);
    List<RoomPo> getRoomWaste(RoomBo roomBo);
    Integer updateRoomWastePrice(List<RoomBo> roomBoList);
}
