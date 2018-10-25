package com.happy.service.impl;

import com.happy.dao.RoomDao;
import com.happy.entity.Response;
import com.happy.entity.bo.RoomBo;
import com.happy.entity.dto.RoomDto;
import com.happy.entity.po.RoomPo;
import com.happy.service.BaseService;
import com.happy.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjun
 * @Title: RoomServiceImpl
 * @ProjectName newHappy
 * @Description: 房间操作
 * @date 2018/10/10 14:40
 */

@Service
public class RoomServiceImpl extends BaseService implements RoomService {

    private static final String propertyType = "1";//物业费
    private static final String powerType = "2";//电费
    private static final String waterType = "3";//水费
    private static final String elevatorType = "4";//电梯费
    private static final String energyType = "5";//能源公摊费
    private static final String wasteType = "6";//卫生垃圾费

    @Autowired
    private RoomDao roomDao;

    @Override
    public Response getDefaultRoom(String token) {
        String customerId="460901004080357376";
        String roomId = roomDao.getDefaultBindRoom(customerId);
        RoomPo roomPo = roomDao.getRoomById(roomId);
        return buildSuccesResponse(roomPo);
    }

    @Override
    public Response getRoomInfo(String communityId, String roomName, String type) {
        RoomBo roomBo = new RoomBo();
        roomBo.setCommunityId(communityId);
        roomBo.setRoomName(roomName);
        if (propertyType.equals(type)) {
            List<RoomPo> roomPoList = roomDao.getRoomProperty(roomBo);
            return buildSuccesResponse(roomPoList);
        }
        else if (powerType.equals(type)) {
            List<RoomPo> roomPoList = roomDao.getRoomPower(roomBo);
            return buildSuccesResponse(roomPoList);
        }
        else if (waterType.equals(type)) {
            List<RoomPo> roomPoList = roomDao.getRoomWater(roomBo);
            return buildSuccesResponse(roomPoList);
        }
        else if (elevatorType.equals(type)) {
            List<RoomPo> roomPoList = roomDao.getRoomElevator(roomBo);
            return buildSuccesResponse(roomPoList);
        }
        else if (energyType.equals(type)) {
            List<RoomPo> roomPoList = roomDao.getRoomEnergy(roomBo);
            return buildSuccesResponse(roomPoList);
        }
        else if (wasteType.equals(type)) {
            List<RoomPo> roomPoList = roomDao.getRoomWaste(roomBo);
            return buildSuccesResponse(roomPoList);
        }
        return buildSuccesResponse();
    }


    @Override
    public Response updateRoomPrice(List<RoomDto> roomDtoList) {
        List<RoomBo> roomBoList = new ArrayList<>();
        String type = "";
        if (roomDtoList != null&&roomDtoList.size() > 0) {
            type = roomDtoList.get(0).getType();
            for (RoomDto roomDto: roomDtoList) {
                RoomBo roomBo = new RoomBo();
                roomBo.setRoomId(roomDto.getRoomId());
                roomBo.setPrice(roomDto.getPrice());
                roomBoList.add(roomBo);
            }
        }
        if (propertyType.equals(type)) {
            roomDao.updateRoomPropertyPrice(roomBoList);
        }
        else if (powerType.equals(type)) {
            roomDao.updateRoomPowerPrice(roomBoList);
        }
        else if (waterType.equals(type)) {
            roomDao.updateRoomWaterPrice(roomBoList);
        }
        else if (elevatorType.equals(type)) {
            roomDao.updateRoomElevatorPrice(roomBoList);
        }
        else if (energyType.equals(type)) {
            roomDao.updateRoomEnergyPrice(roomBoList);
        }
        else if (wasteType.equals(type)) {
            roomDao.updateRoomWastePrice(roomBoList);
        }
        return buildSuccesResponse();
    }
}
