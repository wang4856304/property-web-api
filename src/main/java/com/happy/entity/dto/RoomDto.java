package com.happy.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author wangjun
 * @Title: RoomDto
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/22 18:06
 */

@Data
@NoArgsConstructor
public class RoomDto {

    @NotBlank(message = "房间编号为空")
    private String roomId;
    @NotNull(message = "价格为空")
    private Double price;
    @NotBlank(message = "类型编号为空")
    private String type;
}
