package com.happy.dao.impl;

import com.happy.dao.BaseDao;
import com.happy.dao.OwnerDao;
import com.happy.entity.bo.OwnerBo;
import com.happy.entity.po.OwnerPo;
import org.springframework.stereotype.Repository;

/**
 * @author wangjun
 * @Title: OwnerDaoImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 17:18
 */

@Repository
public class OwnerDaoImpl extends BaseDao implements OwnerDao {
    @Override
    public OwnerPo getOwner(OwnerBo ownerBo) {
        return masterSqlSessionTemplate.selectOne("Owner.getOwner", ownerBo);
    }

    @Override
    public Integer addOwner(OwnerBo ownerBo) {
        return masterSqlSessionTemplate.insert("Owner.addOwner", ownerBo);
    }
}
