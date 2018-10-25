package com.happy.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjun
 * @Title: SequenceBo
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/11 12:24
 */

@Data
@NoArgsConstructor
public class SequenceBo {
    private long seqId;
    private String sequenceName;
}
