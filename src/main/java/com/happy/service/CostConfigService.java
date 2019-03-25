package com.happy.service;

import com.happy.entity.Response;
import com.happy.entity.dto.CostConfigDto;
import com.happy.entity.dto.CostConfigModeDto;

/**
 * @author wangjun
 * @Title: CostConfigService
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/19 20:14
 */
public interface CostConfigService {

    Response setCostConfig(CostConfigDto costConfigDto);
    Response setCostConfigMode(CostConfigModeDto costConfigModeDto);
    Response getCostConfigMode();
}
