package com.happy.controller;

import com.happy.entity.dto.ParkingSpaceManagerDto;
import com.happy.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wangjun
 * @Title: ParkingSpaceController
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 19:14
 */

@RestController
@RequestMapping("/parkingSpace")
public class ParkingSpaceController extends BaseController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @RequestMapping("/addParkingSpace")
    public Object addParkingSpace(@Valid @RequestBody ParkingSpaceManagerDto parkingSpaceDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return buildValidErrorJson(bindingResult);
        }
        return buildResponse(parkingSpaceService.addParkingSpace(parkingSpaceDto));
    }
}
