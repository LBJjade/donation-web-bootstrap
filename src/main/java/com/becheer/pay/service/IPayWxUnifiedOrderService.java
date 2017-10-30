package com.becheer.pay.service;

import com.becheer.pay.model.PayWxUnifiedOrder;

import java.util.Map;

public interface IPayWxUnifiedOrderService {

    int insert(Map<String, String> map);

    int insert(PayWxUnifiedOrder model);

    int update(Map<String, String> map);

    int update(PayWxUnifiedOrder model);

    PayWxUnifiedOrder getPayWxUnifiedOrderByOutTradeNo(String outTradeNo);
}

