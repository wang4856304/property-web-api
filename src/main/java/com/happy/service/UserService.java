package com.happy.service;

import com.happy.entity.Response;
import com.happy.entity.dto.UserChangePwdDto;
import com.happy.entity.dto.UserDto;
import com.happy.entity.dto.UserLoginDto;
import com.happy.entity.dto.UserResetPwdDto;

/**
 * }
 *
 * @author Administrator
 * @Description:
 * @date 2018/8/17 11:44
 */
public interface UserService {
    //Response registerUser(UserDto userDto);
    Response login(String userName, String password, String ip);
    Response loginOut(String token);
    Response getUserInfo(String token);
    //Response changeUserPassword(UserChangePwdDto userChangePwdDto);
    //Response resetUserPassword(UserResetPwdDto userResetPwdDto);
    //String getUserIdByToken(String token);
}
