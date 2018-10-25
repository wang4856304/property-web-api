package com.happy.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjun
 * @Title: MenuPo
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/17 21:11
 */

@Data
@NoArgsConstructor
public class MenuPo {
    private String title;
    private String href;
    private String icon;
    private int spRead;
}
