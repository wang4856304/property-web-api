package com.happy.service.impl;

import com.happy.constant.SequenceConstant;
import com.happy.dao.SequenceDao;
import com.happy.entity.bo.SequenceBo;
import com.happy.service.BaseService;
import com.happy.service.SequenceService;
import com.happy.service.redis.RedisService;
import com.happy.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author wangjun
 * @Title: SequenceServiceImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/11 10:47
 */

@Service
public class SequenceServiceImpl extends BaseService implements SequenceService {
    @Autowired
    private SequenceDao sequenceDao;

    @Autowired
    private RedisService redisService;

    @Override
    public Long selectSequence(String sequenceName) {
        SequenceBo sequenceBo = new SequenceBo();
        sequenceBo.setSequenceName(sequenceName);
        return sequenceDao.selectSequence(sequenceBo);
    }

    /**
     * 根据规则生成22位订单号
     * @return
     */
    @Override
    public String createOrderSequence(String prefix) {
        String key = SequenceConstant.ORDER_SEQ_NAME + DateUtil.formartDate(new Date(), DateUtil.YYYYMMDD);
        Long seq = redisService.getOrderSeq(key);
        StringBuffer sb = new StringBuffer();
        DecimalFormat decimalFormat = new DecimalFormat("000000000");
        if (!StringUtils.isEmpty(prefix)) {
            sb.append(prefix);
        }
        sb.append(DateUtil.formartDate(new Date(), DateUtil.YYYYMMDD));
        sb.append(decimalFormat.format(seq));
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int value = random.nextInt(9);
            sb.append(value);
        }
        return sb.toString();
    }
}
