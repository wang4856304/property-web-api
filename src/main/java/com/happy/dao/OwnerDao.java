package com.happy.dao;

import com.happy.entity.bo.OwnerBo;
import com.happy.entity.po.OwnerPo;

/**
 * @author wangjun
 * @Title: OwnerDao
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/18 17:18
 */
public interface OwnerDao {

    OwnerPo getOwner(OwnerBo ownerBo);
    Integer addOwner(OwnerBo ownerBo);
}
