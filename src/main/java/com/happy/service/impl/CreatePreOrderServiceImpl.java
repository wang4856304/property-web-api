package com.happy.service.impl;

import com.happy.constant.Constant;
import com.happy.constant.OrderConstant;
import com.happy.dao.CreatePreOrderDao;
import com.happy.entity.Response;
import com.happy.entity.bo.OrderBo;
import com.happy.entity.po.RoomCostConfigPo;
import com.happy.entity.po.RoomPo;
import com.happy.service.BaseService;
import com.happy.service.CreatePreOrderService;
import com.happy.service.SequenceService;
import com.happy.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangjun
 * @Title: CreatePreOrderServiceImpl
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/11 10:50
 */

@Service
public class CreatePreOrderServiceImpl extends BaseService implements CreatePreOrderService {

    @Autowired
    private CreatePreOrderDao createPreOrderDao;

    @Autowired
    private SequenceService sequenceService;

    @Override
    @Transactional
    public Response createPreOrder(String communityId) {
        //String communityId = "767813892";
        List<RoomPo> roomPos = createPreOrderDao.getRoomListByCommunity(communityId);
        List<String> roomIds = new ArrayList<>();
        if (roomPos != null&&roomPos.size() > 0) {
            for (RoomPo roomPo: roomPos) {
                roomIds.add(roomPo.getRoomId());
            }
        }
        List<RoomCostConfigPo> roomWaterCostConfigPos = null;
        List<RoomCostConfigPo> roomWasteCostConfigPos = null;
        List<RoomCostConfigPo> roomPowerCostConfigPos = null;
        if (roomIds.size() > 0) {
            roomWaterCostConfigPos = createPreOrderDao.getRoomWaterConfigList(roomIds);
            roomWasteCostConfigPos = createPreOrderDao.getRoomWasteConfigList(roomIds);
            roomPowerCostConfigPos = createPreOrderDao.getRoomPowerConfigList(roomIds);
        }

        if (roomWaterCostConfigPos != null&&roomWaterCostConfigPos.size() > 0) {

            List<OrderBo> orderBoList = createOrder(roomWaterCostConfigPos, Constant.WATER_COST_TYPE);
            createWaterOrder(orderBoList);
        }

        if (roomWasteCostConfigPos != null&&roomWasteCostConfigPos.size() > 0) {
            List<OrderBo> orderBoList = createOrder(roomWaterCostConfigPos, Constant.WASTE_COST_TYPE);
            createWasteOrder(orderBoList);
        }

        if (roomPowerCostConfigPos != null&&roomPowerCostConfigPos.size() > 0) {
            List<OrderBo> orderBoList = createOrder(roomWaterCostConfigPos, Constant.POWER_COST_TYPE);
            createPowerOrder(orderBoList);
        }
        long endTime = System.currentTimeMillis();
        return buildSuccesResponse();
    }

    /**
     * 根据规则生成22位订单号
     * @return
     */
    public String createOrderId() {
        String seq = sequenceService.createOrderSequence(OrderConstant.PRE_ORDER_PREFIX);
        return seq;
    }

    /**
     *
     * @param roomCostConfigPos
     * @param costType 费用类型
     * @return
     */
    public List<OrderBo> createOrder(List<RoomCostConfigPo> roomCostConfigPos, int costType) {
        List<OrderBo> orderBoList = new ArrayList<>();
        if (roomCostConfigPos != null&&roomCostConfigPos.size() > 0) {
            for (RoomCostConfigPo roomCostConfigPo: roomCostConfigPos) {
                OrderBo orderBo = new OrderBo();
                String orderId = createOrderId();
                orderBo.setOrderId(orderId);
                orderBo.setRoomId(Long.valueOf(roomCostConfigPo.getRoomId()));
                orderBo.setConfigurationId(Long.valueOf(roomCostConfigPo.getRoomId()));
                orderBo.setPrice(String.valueOf(roomCostConfigPo.getPrice()));
                Date nowDate = new Date();
                orderBo.setCreateTime(nowDate);
                orderBo.setYear(Integer.valueOf(DateUtil.getCurYear(nowDate)));
                orderBo.setMonth(Integer.valueOf(DateUtil.getCurMonth(nowDate)));
                orderBoList.add(orderBo);
            }
        }
        return orderBoList;
    }


    public void createWaterOrder(List<OrderBo> orderBoList) {
        if (orderBoList != null&&orderBoList.size() > 0) {
            createPreOrderDao.createWaterOrder(orderBoList);
        }
    }

    public void createWasteOrder(List<OrderBo> orderBoList) {
        if (orderBoList != null&&orderBoList.size() > 0) {
            createPreOrderDao.createWasteOrder(orderBoList);
        }
    }

    public void createPowerOrder(List<OrderBo> orderBoList) {
        if (orderBoList != null&&orderBoList.size() > 0) {
            createPreOrderDao.createPowerOrder(orderBoList);
        }
    }
}
