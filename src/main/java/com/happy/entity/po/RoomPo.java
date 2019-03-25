package com.happy.entity.po;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjun
 * @Title: RoomPo
 * @ProjectName newHappy
 * @Description: TODO
 * @date 2018/10/10 14:19
 */
@Data
@NoArgsConstructor
public class RoomPo {

    /**
     * 房间编号
     */
    private String roomId;

    /**
     * 房间名称
     */
    private String roomName;

    /**
     * 楼层编号
     */
    private String layerId;

    /**
     * 楼层名称
     */
    private String layerName;

    /**
     * 楼层编号
     */
    private String unitId;

    /**
     * 楼层名称
     */
    private String unitName;

    /**
     * 楼编号
     */
    private String floorId;

    /**
     * 楼名称
     */
    private String floorName;

    /**
     * 小区编号
     */
    private String communityId;

    /**
     * 小区名称
     */
    private String communityName;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 房屋面积
     */
    private Double acreage;

    /**
     * 物业费单价
     */
    private Double price;

    /**
     * 业主姓名
     */
    private String ownerName;

    /**
     * 业主电话
     */
    private String ownerTel;

    /**
     * 模式
     */
    private Integer chargeType;

    private String standard;
}
