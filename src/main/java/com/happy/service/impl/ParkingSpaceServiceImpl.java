package com.happy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.happy.dao.OwnerDao;
import com.happy.dao.ParkingSpaceDao;
import com.happy.entity.Response;
import com.happy.entity.bo.OwnerBo;
import com.happy.entity.bo.ParkingSpaceBo;
import com.happy.entity.dto.ParkingSpaceManagerDto;
import com.happy.enums.BusinessErrorEnum;
import com.happy.exception.BusinessException;
import com.happy.service.BaseService;
import com.happy.service.ParkingSpaceService;
import com.happy.service.SnowflakeIdGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangjun
 * @Title: ParkingSpaceServiceImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 19:31
 */

@Service
public class ParkingSpaceServiceImpl extends BaseService implements ParkingSpaceService {

    @Autowired
    private ParkingSpaceDao parkingSpaceDao;

    @Autowired
    private OwnerDao ownerDao;

    @Autowired
    private SnowflakeIdGenerateService snowflakeIdGenerateService;

    private static final String BIND_EXIST_OWNER = "2";//绑定的业主

    @Override
    @Transactional
    public Response addParkingSpace(ParkingSpaceManagerDto parkingSpaceDto) {
        ParkingSpaceBo parkingSpaceBo = JSONObject.parseObject(JSONObject.toJSONString(parkingSpaceDto), ParkingSpaceBo.class);
        if (BIND_EXIST_OWNER.equals(parkingSpaceDto.getThisParkType())) {
            try {
                parkingSpaceDao.insertParkingSpace(parkingSpaceBo);
                return buildSuccesResponse();
            }
            catch (DuplicateKeyException e) {
                throw new BusinessException(BusinessErrorEnum.PARK_SPACE_CODE_REPEAT_ERROR.getCode(), BusinessErrorEnum.PARK_SPACE_CODE_REPEAT_ERROR.getMsg(), e);
            }
        }
        else {
            try {
                OwnerBo ownerBo = JSONObject.parseObject(JSONObject.toJSONString(parkingSpaceDto), OwnerBo.class);
                String ownerId = String.valueOf(snowflakeIdGenerateService.nextId());
                ownerBo.setOwnerId(ownerId);
                parkingSpaceBo.setOwnerId(ownerId);
                ownerDao.addOwner(ownerBo);
            }
            catch (DuplicateKeyException e) {
                throw new BusinessException(BusinessErrorEnum.OWNER_REPEAT_ERROR.getCode(), BusinessErrorEnum.OWNER_REPEAT_ERROR.getMsg(), e);

            }
            try {
                parkingSpaceDao.insertParkingSpace(parkingSpaceBo);
            }
            catch (DuplicateKeyException e) {
                throw new BusinessException(BusinessErrorEnum.PARK_SPACE_CODE_REPEAT_ERROR.getCode(), BusinessErrorEnum.PARK_SPACE_CODE_REPEAT_ERROR.getMsg(), e);
            }
        }
        return buildSuccesResponse();
    }
}
