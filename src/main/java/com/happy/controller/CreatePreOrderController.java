package com.happy.controller;

import com.happy.service.CreatePreOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/preOrder")
public class CreatePreOrderController extends BaseController {

    @Autowired
    private CreatePreOrderService createPreOrderService;

    @RequestMapping("/createOrder")
    public Object createPreOrder(@RequestParam String communityId) {
        return buildResponse(createPreOrderService.createPreOrder(communityId));
    }
}
