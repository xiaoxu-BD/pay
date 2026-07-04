package com.example.pay.model;

import java.util.Map;

public class PaymentDraft {
    private String provider;
    private String outTradeNo;
    private String nextStep;
    private Map<String, String> requestParameters;

    public PaymentDraft(String provider, String outTradeNo, String nextStep, Map<String, String> requestParameters) {
        this.provider = provider;
        this.outTradeNo = outTradeNo;
        this.nextStep = nextStep;
        this.requestParameters = requestParameters;
    }

    public String getProvider() { return provider; }
    public String getOutTradeNo() { return outTradeNo; }
    public String getNextStep() { return nextStep; }
    public Map<String, String> getRequestParameters() { return requestParameters; }
}
