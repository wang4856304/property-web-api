package com.happy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.happy.constant.UserConstant;
import com.happy.dao.UserDao;
import com.happy.entity.Response;
import com.happy.entity.bo.UserBo;
import com.happy.entity.dto.UserChangePwdDto;
import com.happy.entity.dto.UserDto;
import com.happy.entity.dto.UserLoginDto;
import com.happy.entity.dto.UserResetPwdDto;
import com.happy.entity.po.UserPo;
import com.happy.enums.ErrorEnum;
import com.happy.enums.ResultEnum;
import com.happy.service.BaseService;
import com.happy.service.UserService;
import com.happy.service.redis.RedisService;
import com.happy.utils.DateUtil;
import com.happy.utils.HttpClientUtil;
import com.happy.utils.TokenUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;


/**
 * }
 *
 * @author Administrator
 * @Description:
 * @date 2018/8/17 11:48
 */

@Service
public class UserServiceImpl extends BaseService implements UserService {

    private static Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;

    @Override
    @Transactional
    public Response login(String userName, String password, String ip) {
        UserBo userBo = new UserBo();
        userBo.setUserName(userName);
        userBo.setPassword(password);
        UserPo userPo = userDao.getUserByNameAndPwd(userBo);
        if (userPo == null) {
            return buildErrorResponse(ErrorEnum.USER_NAME_PASSWORD_ERROR.getCode(), ErrorEnum.USER_NAME_PASSWORD_ERROR.getMsg());
        }
        if (userPo.getIsActivation().equals(1)) {
            return buildErrorResponse(ErrorEnum.USER_FREEZEN_ERROR.getCode(), ErrorEnum.USER_FREEZEN_ERROR.getMsg());
        }
        userBo.setIp(ip);
        userBo.setLastLoginTime(DateUtil.formartDate(new Date(), DateUtil.YYYY_MM_DD_HH_MM_SS));
        userDao.updateUserLoginInfo(userBo);
        String token = redisService.get(userPo.getManagerId());
        if (StringUtils.isEmpty(token)) {
            token = TokenUtil.getUUID32();
            redisService.set(userPo.getManagerId(), token, 7*24*3600);
            redisService.set(token, userPo.getManagerId(), 7*24*3600);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("communityId", userPo.getCommunityId());
        return buildSuccesResponse(jsonObject);
    }

    @Override
    public Response loginOut(String token) {
        String managerId = redisService.get(token);
        if (!StringUtils.isEmpty(managerId)) {
            redisService.del(token);
            redisService.del(managerId);
        }
        return buildSuccesResponse();
    }

    @Override
    public Response getUserInfo(String token) {
        String managerId = redisService.get(token);
        UserPo userPo = userDao.getUserInfoById(managerId);
        return buildSuccesResponse(userPo);
    }

}
