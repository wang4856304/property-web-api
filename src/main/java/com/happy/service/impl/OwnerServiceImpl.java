package com.happy.service.impl;

import com.happy.dao.OwnerDao;
import com.happy.entity.Response;
import com.happy.entity.bo.OwnerBo;
import com.happy.entity.dto.OwnerDto;
import com.happy.entity.po.OwnerPo;
import com.happy.enums.BusinessErrorEnum;
import com.happy.service.BaseService;
import com.happy.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjun
 * @Title: OwnerServiceImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 17:22
 */

@Service
public class OwnerServiceImpl extends BaseService implements OwnerService {

    @Autowired
    private OwnerDao ownerDao;

    @Override
    public Response getOwner(String communityId, String idCardNumber) {
        OwnerBo ownerBo = new OwnerBo();
        ownerBo.setCommunityId(communityId);
        ownerBo.setIdCardNumber(idCardNumber);
        OwnerPo ownerPo = ownerDao.getOwner(ownerBo);
        if (ownerPo == null) {
            return buildErrorResponse(BusinessErrorEnum.OWNER_NOT_FOUND_ERROR.getCode(), BusinessErrorEnum.OWNER_NOT_FOUND_ERROR.getMsg());
        }
        return buildSuccesResponse(ownerPo);
    }

    @Override
    public Response addOwner(OwnerDto ownerDto) {
        OwnerBo ownerBo = (OwnerBo)ownerDto;
        ownerDao.addOwner(ownerBo);
        return buildSuccesResponse();
    }
}
