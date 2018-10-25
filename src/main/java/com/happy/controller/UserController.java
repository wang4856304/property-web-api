package com.happy.controller;

import com.happy.entity.Response;
import com.happy.entity.dto.UserChangePwdDto;
import com.happy.entity.dto.UserDto;
import com.happy.entity.dto.UserLoginDto;
import com.happy.entity.dto.UserResetPwdDto;
import com.happy.service.UserService;
import com.happy.utils.NetUtil;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("/login")
    public Object login(@RequestParam @NotBlank(message = "用户名为空") String userName, @RequestParam @NotBlank(message = "密码为空")String passWord, String ip) {
        return buildResponse(userService.login(userName, passWord, ip));
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
