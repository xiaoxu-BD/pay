package com.example.pay.controller;

import com.example.pay.model.CreateOrderRequest;
import com.example.pay.model.PaymentDraft;
import com.example.pay.service.PaymentLearningService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/pay")
public class PaymentLearningController {
    private final PaymentLearningService paymentLearningService;

    public PaymentLearningController(PaymentLearningService paymentLearningService) {
        this.paymentLearningService = paymentLearningService;
    }

    @GetMapping("/flow")
    public Map<String, String> flow() {
        return paymentLearningService.describeFlow();
    }

    @PostMapping("/wx/native")
    public PaymentDraft wxNative(@RequestBody CreateOrderRequest request) {
        return paymentLearningService.buildWxNativeDraft(request);
    }

    @PostMapping("/ali/page")
    public PaymentDraft aliPage(@RequestBody CreateOrderRequest request) {
        return paymentLearningService.buildAliPageDraft(request);
    }

    @PostMapping("/wx/notify")
    public String wxNotify(@RequestBody String body) {
        return "收到微信支付通知：学习骨架中请在这里验签、核对金额并幂等更新订单。";
    }

    @PostMapping("/ali/notify")
    public String aliNotify(@RequestBody String body) {
        return "success";
    }
}
