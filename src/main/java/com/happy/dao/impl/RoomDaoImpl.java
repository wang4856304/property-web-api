package com.happy.dao.impl;

import com.happy.dao.BaseDao;
import com.happy.dao.RoomDao;
import com.happy.entity.bo.RoomBo;
import com.happy.entity.po.RoomPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangjun
 * @Title: RoomDaoImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/10 15:08
 */

@Repository
public class RoomDaoImpl extends BaseDao implements RoomDao {
    @Override
    public String getDefaultBindRoom(@Param("roomId")String customerId) {
        return masterSqlSessionTemplate.selectOne("Room.getDefaultBindRoom", customerId);
    }

    @Override
    public List<String> getRoomList(@Param("roomId")String customerId) {
        return masterSqlSessionTemplate.selectList("Room.getRoomList", customerId);
    }

    @Override
    public RoomPo getRoomById(@Param("roomId")String roomId) {
        return masterSqlSessionTemplate.selectOne("Room.getRoomById", roomId);
    }

    @Override
    public Integer updateDefaultBindRoom(RoomBo roomBo) {
        return masterSqlSessionTemplate.update("Room.updateDefaultBindRoom", roomBo);
    }

    @Override
    public Integer updateOtherRoomDefault(RoomBo roomBo) {
        return masterSqlSessionTemplate.update("Room.updateOtherRoomDefault", roomBo);
    }

    @Override
    public Integer bindRoom(RoomBo roomBo) {
        return masterSqlSessionTemplate.insert("Room.bindRoom", roomBo);
    }

    @Override
    public Integer deleteBindRoom(RoomBo roomBo) {
        return masterSqlSessionTemplate.delete("Room.deleteBindRoom", roomBo);
    }

    @Override
    public List<RoomPo> getRoomProperty(RoomBo roomBo) {
        return masterSqlSessionTemplate.selectList("Room.getRoomProperty", roomBo);
    }

    @Override
    public Integer updateRoomPropertyPrice(List<RoomBo> roomBoList) {
        return masterSqlSessionTemplate.update("Room.updateRoomPropertyPrice", roomBoList);
    }

    @Override
    public List<RoomPo> getRoomPower(RoomBo roomBo) {
        return masterSqlSessionTemplate.selectList("Room.getRoomPower", roomBo);
    }

    @Override
    public Integer updateRoomPowerPrice(List<RoomBo> roomBoList) {
        return masterSqlSessionTemplate.update("Room.updateRoomPowerPrice", roomBoList);
    }

    @Override
    public List<RoomPo> getRoomWater(RoomBo roomBo) {
        return masterSqlSessionTemplate.selectList("Room.getRoomWater", roomBo);
    }

    @Override
    public Integer updateRoomWaterPrice(List<RoomBo> roomBoList) {
        return masterSqlSessionTemplate.update("Room.updateRoomWaterPrice", roomBoList);
    }

    @Override
    public List<RoomPo> getRoomElevator(RoomBo roomBo) {
        return masterSqlSessionTemplate.selectList("Room.getRoomElevator", roomBo);
    }

    @Override
    public Integer updateRoomElevatorPrice(List<RoomBo> roomBoList) {
        return masterSqlSessionTemplate.update("Room.updateRoomElevatorPrice", roomBoList);
    }

    @Override
    public List<RoomPo> getRoomEnergy(RoomBo roomBo) {
        return masterSqlSessionTemplate.selectList("Room.getRoomEnergy", roomBo);
    }

    @Override
    public Integer updateRoomEnergyPrice(List<RoomBo> roomBoList) {
        return masterSqlSessionTemplate.update("Room.updateRoomEnergyPrice", roomBoList);
    }

    @Override
    public List<RoomPo> getRoomWaste(RoomBo roomBo) {
        return masterSqlSessionTemplate.selectList("Room.getRoomWaste", roomBo);
    }

    @Override
    public Integer updateRoomWastePrice(List<RoomBo> roomBoList) {
        return masterSqlSessionTemplate.update("Room.updateRoomWastePrice", roomBoList);
    }
}
