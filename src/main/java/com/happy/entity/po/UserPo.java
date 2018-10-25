package com.happy.entity.po;

import com.happy.entity.bo.UserBo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjun
 * @Title: UserPo
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/17 16:59
 */

@Data
@NoArgsConstructor
public class UserPo extends UserBo {
    private String managerId;
    private Integer isActivation;
    private String userName;
    private String communityId;
    private String communityName;
    private String companyId;
    private String companyName;
    private String companyLogUrl;
}
