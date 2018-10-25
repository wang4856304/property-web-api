package com.happy.service;

/**
 * @author wangjun
 * @Title: SequenceService
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/11 10:47
 */
public interface SequenceService {

    Long selectSequence(String sequenceName);
    String createOrderSequence(String prefix);
}
