package com.happy.controller;

import com.happy.entity.dto.CostConfigDto;
import com.happy.entity.dto.CostConfigModeDto;
import com.happy.service.CostConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wangjun
 * @Title: CostConfigController
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/19 19:11
 */

@RestController
@RequestMapping("/costConfig")
public class CostConfigController extends BaseController {

    @Autowired
    private CostConfigService costConfigService;

    @RequestMapping("/setCostConfig")
    public Object setCostConfig(@Valid @RequestBody CostConfigDto costConfigDto) {
        return buildResponse(costConfigService.setCostConfig(costConfigDto));
    }

    /**
     * 收费方式配置
     * @param costConfigModeDto
     * @return
     */
    @RequestMapping("/setCostConfigMode")
    public Object setCostConfigMode(@Valid @RequestBody CostConfigModeDto costConfigModeDto) {
        return buildResponse(costConfigService.setCostConfigMode(costConfigModeDto));
    }

    @RequestMapping("/getCostConfigMode")
    public Object getCostConfigMode() {
        return buildResponse(costConfigService.getCostConfigMode());
    }
}
