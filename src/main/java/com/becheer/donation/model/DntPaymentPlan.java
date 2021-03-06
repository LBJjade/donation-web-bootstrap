package com.becheer.donation.model;

import java.util.Date;

public class DntPaymentPlan {

    private Long id;     // 付款计划标识
    private String title;     //标题
    private String refTable; //捐款类型
    private Integer refRecordId ; //捐款合同
    private Date paymentDate ; //付款日期
    private Integer amount ; //应付金额
    private Integer receivedAmount ; //已收金额
    private Date deadline ; //截止日期
    private String remark ; //备注
    private Integer enable ; //状态
    private Date createTime ; //创建时间
    private Integer createBy ; //创建人
    private Date updateTime ; //修改时间
    private Integer updateBy ; //修改人
    private Integer paymentMethodId ; //付款方式标识
    private String paylogRefTable ; //付款方式流水记录表
    private Integer paylogRefRecordId ; //付款方式流水记录标识
    private Integer status ; //状态 0未捐赠 1已捐赠'
    private String orderNo; //订单号

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRefTable() {
        return refTable;
    }

    public void setRefTable(String refTable) {
        this.refTable = refTable;
    }

    public Integer getRefRecordId() {
        return refRecordId;
    }

    public void setRefRecordId(Integer refRecordId) {
        this.refRecordId = refRecordId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Integer receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaylogRefTable() {
        return paylogRefTable;
    }

    public void setPaylogRefTable(String paylogRefTable) {
        this.paylogRefTable = paylogRefTable;
    }

    public Integer getPaylogRefRecordId() {
        return paylogRefRecordId;
    }

    public void setPaylogRefRecordId(Integer paylogRefRecordId) {
        this.paylogRefRecordId = paylogRefRecordId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
