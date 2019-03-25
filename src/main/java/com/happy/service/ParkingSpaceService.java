package com.happy.service;

import com.happy.entity.Response;
import com.happy.entity.dto.ParkingSpaceManagerDto;

/**
 * @author wangjun
 * @Title: ParkingSpaceService
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 19:31
 */
public interface ParkingSpaceService {

    Response addParkingSpace(ParkingSpaceManagerDto parkingSpaceDto);
}
