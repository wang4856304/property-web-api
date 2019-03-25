package com.happy.service;

import com.happy.entity.Response;
import com.happy.entity.dto.RoomDto;
import com.happy.entity.po.RoomPo;

import java.util.List;

/**
 * @author wangjun
 * @Title: RoomService
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/10 14:13
 */
public interface RoomService {

    Response getDefaultRoom(String token);
    Response getRoomInfo(String communityId, String roomName, String type);
    Response updateRoomPrice(List<RoomDto> roomDtoList);
}
