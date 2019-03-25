package com.happy.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjun
 * @Title: OwnerBo
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 17:14
 */

@Data
@NoArgsConstructor
public class OwnerBo {

    private String communityId;

    private String idCardNumber;

    private String ownerId;

    private String ownerName;

    private String telephone;

    private Integer sex;

    private String country;

    private String province;

    private String city;

    private String area;

    private String address;
}
