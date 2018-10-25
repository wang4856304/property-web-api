package com.happy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.happy.dao.CommunityDao;
import com.happy.dao.FloorDao;
import com.happy.dao.UnitDao;
import com.happy.entity.Response;
import com.happy.entity.po.CommunityPo;
import com.happy.entity.po.FloorPo;
import com.happy.entity.po.UnitPo;
import com.happy.enums.BusinessErrorEnum;
import com.happy.service.BaseService;
import com.happy.service.CommunityService;
import com.happy.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wangjun
 * @Title: CommunityServiceImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 10:19
 */

@Service
public class CommunityServiceImpl extends BaseService implements CommunityService {

    @Autowired
    private CommunityDao communityDao;

    @Autowired
    private RedisService redisService;

    @Autowired
    private FloorDao floorDao;

    @Autowired
    private UnitDao unitDao;

    @Override
    public Response getCommunityMode(String communityId) {
        CommunityPo communityPo = communityDao.getCommunityById(communityId);
        if (communityPo == null) {
            return buildErrorResponse(BusinessErrorEnum.NO_COMMUNITY_ERROR.getCode(), BusinessErrorEnum.NO_COMMUNITY_ERROR.getMsg());
        }
        JSONObject jsonObject = new JSONObject();
        String mode = redisService.get("mode:" + communityId);
        if (!StringUtils.isEmpty(mode)) {
            jsonObject = JSONObject.parseObject(mode);
            /*JSONObject carDataJson = jsonObject.getJSONObject("carData");
            JSONObject costConfigJson = jsonObject.getJSONObject("formObject");
            JSONArray floorDataArr = jsonObject.getJSONArray("floorData");
            if (carDataJson == null||costConfigJson == null||floorDataArr == null) {
                return buildErrorResponse(BusinessErrorEnum.COST_CONFIG_NOT_FOUND_ERROR.getCode(), BusinessErrorEnum.COST_CONFIG_NOT_FOUND_ERROR.getMsg());
            }*/
        }
        else {
            return buildErrorResponse(BusinessErrorEnum.COST_CONFIG_NOT_FOUND_ERROR.getCode(), BusinessErrorEnum.COST_CONFIG_NOT_FOUND_ERROR.getMsg());
        }
        return buildSuccesResponse(jsonObject);
    }

    @Override
    public Response getFloorList(String communityId) {
        List<FloorPo> floorPos = floorDao.getFloorListByCommunityId(communityId);
        return buildSuccesResponse(floorPos);
    }

    @Override
    public Response getUnitList(String floorId) {
        List<UnitPo> unitPoList = unitDao.getUnitListByFloorId(floorId);
        return buildSuccesResponse(unitPoList);
    }
}
