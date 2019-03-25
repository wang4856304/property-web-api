package com.happy.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.happy.entity.Response;
import com.happy.entity.dto.RoomDto;
import com.happy.service.RoomService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wangjun
 * @Title: RoomController
 * @ProjectName newHappy
 * @Description: 房间操作
 * @date 2018/10/10 14:10
 */

@RestController
@RequestMapping("/room")
@Validated
public class RoomController extends BaseController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/getDefaultRoom")
    public Object getDefaultRoom(@RequestHeader("token") String token) {
        return buildResponse(roomService.getDefaultRoom(token));
    }

    @RequestMapping("/getRoomInfo/{communityId}/{roomName}/{type}")
    public Object getRoomInfo(@NotBlank(message = "小区编号为空")@PathVariable String communityId,
                              @NotBlank(message = "房间名称为空")@PathVariable String roomName, @NotBlank(message = "费用类型为空")@PathVariable String type) {
        return buildResponse(roomService.getRoomInfo(communityId, roomName, type));
    }

    @RequestMapping("/updateRoomPrice")
    public Object updateRoomPrice(@Valid @RequestBody List<RoomDto> roomDtoList) {
        return buildResponse(roomService.updateRoomPrice(roomDtoList));
    }
}
