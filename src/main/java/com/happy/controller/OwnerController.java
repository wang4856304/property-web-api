package com.happy.controller;

import com.happy.entity.dto.OwnerDto;
import com.happy.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wangjun
 * @Title: OwnerController
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 17:26
 */

@RestController
@RequestMapping("/owner")
public class OwnerController extends BaseController {

    @Autowired
    private OwnerService ownerService;

    @RequestMapping("/checkOwner")
    public Object checkOwner(@RequestParam String communityId, @RequestParam String idCardNumber) {
        return buildResponse(ownerService.getOwner(communityId, idCardNumber));
    }

    @RequestMapping("/addOwner")
    public Object addOwner(@Valid @RequestBody OwnerDto ownerDto) {
        return buildResponse(ownerService.addOwner(ownerDto));
    }
}
