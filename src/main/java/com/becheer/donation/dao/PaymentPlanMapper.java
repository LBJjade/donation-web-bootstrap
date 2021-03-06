package com.becheer.donation.dao;

import com.becheer.donation.model.extension.payment.PaymentPlanExtension;
import org.springframework.stereotype.Component;

import java.util.List;

/*
* PaymentPlanMapper
* Creator : xiaokepu
* Date : 2017-10-11
*/
@Component
public interface PaymentPlanMapper {
    List<PaymentPlanExtension> SelectPaymentPlanByContractId(Long contractId);

    /**
     * 根据contractId查金额
     */
    Integer GetAmountByContractId(Long contractId);

    /**
     * 根据paymentPlanId查支付计划对象
     */
    PaymentPlanExtension selectPaymentPlanByaymentPlanId(Long paymentPlanId);

    /**
     * 根据paymentPlanId查金额
     */
    int updateOrderNo(String orderNo ,Long paymentPlanId);
}
