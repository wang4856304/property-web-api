package com.happy.entity.dto;

import com.happy.annotation.IdCardNumValid;
import com.happy.entity.bo.OwnerBo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author wangjun
 * @Title: OwnerDto
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 17:58
 */

@Data
@NoArgsConstructor
public class OwnerDto extends OwnerBo {

    @NotBlank(message = "小区编号为空")
    private String communityId;

    @IdCardNumValid
    private String idCardNumber;

    private String ownerId;

    @NotBlank(message = "业主姓名为空")
    private String ownerName;

    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$", message = "请输入正确的电话号码")
    private String telephone;

    @NotNull(message = "性别为空")
    private Integer sex;

    @NotBlank(message = "国家为空")
    private String country;

    @NotBlank(message = "省份为空")
    private String province;

    @NotBlank(message = "城市为空")
    private String city;

    @NotBlank(message = "区县为空")
    private String area;

    @NotBlank(message = "详细地址为空")
    private String address;
}
