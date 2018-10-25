package com.happy.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class OrderBo implements Serializable {
    private String orderId;

    private Long roomId;

    private Long configurationId;

    private String price;

    private Date createTime;

    private Integer status;

    private Double usageAmount;

    private String remark;

    private String payFrom;

    private Date payTime;

    private Integer payType;

    private Boolean isCompensate;

    private String createBy;

    private Integer year;

    private Integer month;

    private Long recordId;

    private String transactionId;

    private String backType;
}