package com.happy.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author wangjun
 * @Title: ParkingSpaceManagerDto
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 19:10
 */

@Data
@NoArgsConstructor
public class ParkingSpaceManagerDto {


    @NotBlank(message = "车位类型为空")
    private String parkType;
    @NotBlank(message = "车位编号为空")
    private String parkNumber;
    @NotNull(message = "是否出售为空")
    private Boolean isSale;
    @NotNull(message = "是否空置为空")
    private Boolean isFree;


    @NotBlank(message = "小区编号为空")
    private String communityId;

    @NotBlank(message = "thisParkType为空")
    private String thisParkType;

    //@IdCardNumValid
    private String idCardNumber;

    private String ownerId;

    //@NotBlank(message = "业主姓名为空")
    private String ownerName;

    //@Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$", message = "请输入正确的电话号码")
    private String telephone;

    //@NotNull(message = "性别为空")
    private Integer sex;

    //@NotBlank(message = "国家为空")
    private String country;

    //@NotBlank(message = "省份为空")
    private String province;

    //@NotBlank(message = "城市为空")
    private String city;

    //@NotBlank(message = "区县为空")
    private String area;

    //@NotBlank(message = "详细地址为空")
    private String address;
}
