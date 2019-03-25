package com.happy.service;

import com.happy.entity.Response;
import com.happy.entity.dto.OwnerDto;

/**
 * @author wangjun
 * @Title: OwnerService
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 17:22
 */
public interface OwnerService {

    Response getOwner(String communityId, String idCardNumber);
    Response addOwner(OwnerDto ownerDto);

}
