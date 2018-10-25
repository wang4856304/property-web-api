package com.happy.dao.impl;

import com.happy.dao.BaseDao;
import com.happy.dao.SequenceDao;
import com.happy.entity.bo.SequenceBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wangjun
 * @Title: SequenceDaoImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/11 10:45
 */

@Repository
public class SequenceDaoImpl extends BaseDao implements SequenceDao {
    @Override
    public long selectSequence(SequenceBo sequenceBo) {
        /*Long seq = masterSqlSessionTemplate.selectOne("Sequence.selectSequence", sequenceName);
        if (seq.equals(100000000)) {

        }*/
        masterSqlSessionTemplate.insert("Sequence.selectSequence", sequenceBo);
        return sequenceBo.getSeqId();
    }
}
