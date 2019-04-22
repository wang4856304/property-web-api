package com.happy.controller;

import com.alibaba.fastjson.JSONObject;
import com.happy.entity.Response;
import com.happy.entity.dto.UserChangePwdDto;
import com.happy.entity.dto.UserDto;
import com.happy.entity.dto.UserLoginDto;
import com.happy.entity.dto.UserResetPwdDto;
import com.happy.enums.ResultEnum;
import com.happy.service.UserService;
import com.happy.utils.HttpClientUtil;
import com.happy.utils.NetUtil;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * }
 *
 * @author Administrator
 * @Description:
 * @date 2018/8/16 21:41
 */

@RestController
@RequestMapping("/manager")
@Validated
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Value("${security.oauth2.client.access-token-uri}")
    private String oauthTokenUrl;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.grant-type}")
    private String grantType;

    /**
     * 用户注册
     * @param userDto
     * @param bindingResult
     * @return
     */
    /*@RequestMapping("/register")
    public Object register(@RequestBody @Valid UserDto userDto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return buildValidErrorJson(bindingResult);
        }
        //String remoteIp = NetUtil.getIpAddress(request);
        //String deviceId = request.getHeader("deviceId");
        //String channelId = request.getHeader("channelId");
        //userDto.setDeviceId(deviceId);
        //userDto.setChannelId(channelId);
        //userDto.setRemoteIp(remoteIp);

        Response response = userService.registerUser(userDto);
        return buildResponse(response);
    }*/

    /**
     * 登陆
     * @param userName
     * @param passWord
     * @param ip
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestParam @NotBlank(message = "用户名为空") String userName, @RequestParam @NotBlank(message = "密码为空")String passWord, String ip) {
        //http://localhost:9920/oauth/token?username=user_1&password=123456&grant_type=password&scope=select&client_id=client_2&client_secret=123456
        String url = createLoginUrl(userName, passWord);
        String result = HttpClientUtil.httpPostRequest(url);
        JSONObject json = JSONObject.parseObject(result);
        Response response = new Response();
        if (json.containsKey("access_token")) {
            response.setCode(ResultEnum.SUCCESS.getCode());
            response.setMsg(ResultEnum.SUCCESS.getMsg());
        }
        else {
            response.setCode(ResultEnum.FAIL.getCode());
            response.setMsg(ResultEnum.FAIL.getMsg());
        }
        response.setData(json);
        return buildResponse(response);
    }

    private String createLoginUrl(String userName, String password) {
        StringBuffer sb = new StringBuffer(oauthTokenUrl);
        sb.append("?");
        sb.append("username=" + userName).append("&");
        sb.append("password=" + password).append("&");
        sb.append("grant_type=" + grantType).append("&");
        sb.append("client_id=" + clientId).append("&");
        sb.append("client_secret=" + clientSecret);
        return sb.toString();
    }

    /**
     * 登出
     * @param token
     * @return
     */
    @RequestMapping("/signOut")
    public Object signOut(@RequestHeader String token){

        return buildResponse(userService.loginOut(token));
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @RequestMapping("/getUserInfo")
    public Object getUserInfo(@RequestHeader String token) {
        Response response = userService.getUserInfo(token);
        return buildResponse(response);
    }


    /**
     * 修改密码
     * @param userChangePwdDto
     * @param bindingResult
     * @return
     */
    /*@RequestMapping("/changeUserPassword")
    public Object changeUserPassword(@Valid @RequestBody UserChangePwdDto userChangePwdDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return buildValidErrorJson(bindingResult);
        }
        Response response = userService.changeUserPassword(userChangePwdDto);
        return buildResponse(response);
    }*/
    /*@RequestMapping("/resetUserPassword")
    public Object resetUserPassword(@Valid @RequestBody UserResetPwdDto userResetPwdDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return buildValidErrorJson(bindingResult);
        }
        Response response = userService.resetUserPassword(userResetPwdDto);
        return buildResponse(response);
    }*/

}
