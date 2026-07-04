package com.example.pay.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "payment")
public class PaymentProperties {
    private String notifyBaseUrl = "https://example.com";
    private final WxPay wx = new WxPay();
    private final AliPay ali = new AliPay();

    public String getNotifyBaseUrl() { return notifyBaseUrl; }
    public void setNotifyBaseUrl(String notifyBaseUrl) { this.notifyBaseUrl = notifyBaseUrl; }
    public WxPay getWx() { return wx; }
    public AliPay getAli() { return ali; }

    public static class WxPay {
        private String appId = "your-wx-app-id";
        private String mchId = "your-wx-mch-id";
        private String partnerKey = "your-wx-partner-key";
        public String getAppId() { return appId; }
        public void setAppId(String appId) { this.appId = appId; }
        public String getMchId() { return mchId; }
        public void setMchId(String mchId) { this.mchId = mchId; }
        public String getPartnerKey() { return partnerKey; }
        public void setPartnerKey(String partnerKey) { this.partnerKey = partnerKey; }
    }

    public static class AliPay {
        private String appId = "your-ali-app-id";
        private String privateKey = "your-ali-private-key";
        private String alipayPublicKey = "your-ali-public-key";
        private String serverUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
        public String getAppId() { return appId; }
        public void setAppId(String appId) { this.appId = appId; }
        public String getPrivateKey() { return privateKey; }
        public void setPrivateKey(String privateKey) { this.privateKey = privateKey; }
        public String getAlipayPublicKey() { return alipayPublicKey; }
        public void setAlipayPublicKey(String alipayPublicKey) { this.alipayPublicKey = alipayPublicKey; }
        public String getServerUrl() { return serverUrl; }
        public void setServerUrl(String serverUrl) { this.serverUrl = serverUrl; }
    }
}
