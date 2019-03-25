package com.happy.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjun
 * @Title: UserBo
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/17 16:57
 */

@Data
@NoArgsConstructor
public class UserBo {

    private String userName;
    private String password;
    private String lastLoginTime;
    private String ip;
}
