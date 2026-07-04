package com.example.pay.service;

import com.example.pay.config.PaymentProperties;
import com.example.pay.model.CreateOrderRequest;
import com.example.pay.model.PaymentDraft;
import com.ijpay.core.kit.PayKit;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class PaymentLearningService {
    private static final DateTimeFormatter ORDER_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    private final PaymentProperties properties;

    public PaymentLearningService(PaymentProperties properties) {
        this.properties = properties;
    }

    public PaymentDraft buildWxNativeDraft(CreateOrderRequest request) {
        String outTradeNo = newOutTradeNo("WX");
        Map<String, String> params = new LinkedHashMap<>();
        params.put("appid", properties.getWx().getAppId());
        params.put("mch_id", properties.getWx().getMchId());
        params.put("nonce_str", PayKit.generateStr());
        params.put("body", request.getSubject());
        params.put("out_trade_no", outTradeNo);
        params.put("total_fee", String.valueOf(request.getAmountFen()));
        params.put("spbill_create_ip", "127.0.0.1");
        params.put("notify_url", properties.getNotifyBaseUrl() + "/api/pay/wx/notify");
        params.put("trade_type", "NATIVE");
        params.put("sign", "使用 WxPayKit.createSign(params, partnerKey) 生成后调用统一下单接口");
        return new PaymentDraft("wxpay", outTradeNo, "把参数交给 IJPay WxPayApi.pushOrder，拿到 code_url 后生成二维码。", params);
    }

    public PaymentDraft buildAliPageDraft(CreateOrderRequest request) {
        String outTradeNo = newOutTradeNo("ALI");
        Map<String, String> bizContent = new LinkedHashMap<>();
        bizContent.put("out_trade_no", outTradeNo);
        bizContent.put("total_amount", BigDecimal.valueOf(request.getAmountFen()).movePointLeft(2).toPlainString());
        bizContent.put("subject", request.getSubject());
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        bizContent.put("notify_url", properties.getNotifyBaseUrl() + "/api/pay/ali/notify");
        return new PaymentDraft("alipay", outTradeNo, "把 bizContent 交给 IJPay AliPayApi.page，返回支付宝收银台表单。", bizContent);
    }

    public Map<String, String> describeFlow() {
        Map<String, String> flow = new LinkedHashMap<>();
        flow.put("1-create-order", "业务系统先创建本地订单，状态为待支付，并生成 out_trade_no。");
        flow.put("2-request-channel", "调用微信/支付宝预下单接口，得到二维码、收银台表单或客户端支付参数。");
        flow.put("3-user-pay", "用户在支付渠道完成付款，渠道异步回调 notify_url。");
        flow.put("4-verify-notify", "服务端验签、核对金额和订单号，幂等更新本地订单为已支付。");
        flow.put("5-query-close-refund", "补单查询、超时关单和退款都通过 out_trade_no 串起业务闭环。");
        return flow;
    }

    private String newOutTradeNo(String prefix) {
        return prefix + LocalDateTime.now().format(ORDER_FORMATTER);
    }
}
