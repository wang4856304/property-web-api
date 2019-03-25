package com.happy.dao;

import com.happy.entity.po.CommunityPo;

/**
 * @author wangjun
 * @Title: CommunityDao
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 10:20
 */
public interface CommunityDao {

    CommunityPo getCommunityById(String communityId);
}
