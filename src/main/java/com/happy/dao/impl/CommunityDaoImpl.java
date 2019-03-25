package com.happy.dao.impl;

import com.happy.dao.BaseDao;
import com.happy.dao.CommunityDao;
import com.happy.entity.po.CommunityPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wangjun
 * @Title: CommunityDaoImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 10:21
 */

@Repository
public class CommunityDaoImpl extends BaseDao implements CommunityDao {
    @Override
    public CommunityPo getCommunityById(@Param("communityId") String communityId) {
        return masterSqlSessionTemplate.selectOne("Community.getCommunityById", communityId);
    }
}
