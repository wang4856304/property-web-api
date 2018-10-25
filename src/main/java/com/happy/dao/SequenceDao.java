package com.happy.dao;

import com.happy.entity.bo.SequenceBo;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangjun
 * @Title: SequenceDao
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/11 10:44
 */
public interface SequenceDao {
    long selectSequence(SequenceBo sequenceBo);
}
