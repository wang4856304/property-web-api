package com.happy.controller;

import com.alibaba.fastjson.JSONObject;
import com.happy.entity.Response;
import com.happy.service.redis.RedisService;
import com.wj.idcenter.IdCenterGenerateTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Optional;

/**
 * @author wangjun
 * @date 18-2-9 下午3:33
 * @description
 * @modified by
 */

@RestController
@Api(value = "测试controller", tags = "测试接口")
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private IdCenterGenerateTemplate idCenterGenerateTemplate;

    @RequestMapping("/testIdCenter")
    public Object testIdCenter() {
        Long id = idCenterGenerateTemplate.nextId();
        return id;
    }

    @RequestMapping("/test")
    public Object test(@RequestBody JSONObject jsonObject) {
        return null;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiOperation(value = "测试swagger", httpMethod = "get")
    public Object hello(@RequestParam @ApiParam(name = "name", value = "名称") String name) {

        String s = null;
        if ("zn".equals(name)) {
            Locale locale = Locale.CHINA;
            s = messageSource.getMessage("r.name", null, locale);
        }
        if ("en".equals(name)) {
            Locale locale = Locale.US;
            s = messageSource.getMessage("r.name", null, locale);
        }
        return Optional.ofNullable(s);
    }

    @RequestMapping(value = "/testRes", method = RequestMethod.GET)
    public ResponseEntity<Object> testResponseEntity() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "wangjun");
        Response response = new Response();
        response.setData(jsonObject);
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "asdasdadsadaas");
        return new ResponseEntity<>(buildResponse(response), headers, HttpStatus.OK);
    }
}
