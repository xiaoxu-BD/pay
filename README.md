# IJPay 支付流程学习骨架

这是一个基于 Spring Boot 与 IJPay 的学习项目，参考 IJPay/IJPay-Demo-SpringBoot 的典型分层方式搭建：配置、Controller、Service、支付请求模型与异步通知入口。

## 启动

```bash
mvn spring-boot:run
```

## 学习接口

- `GET /api/pay/flow`：查看支付闭环的关键步骤。
- `POST /api/pay/wx/native`：生成微信 Native 支付预下单参数草稿。
- `POST /api/pay/ali/page`：生成支付宝电脑网站支付业务参数草稿。
- `POST /api/pay/wx/notify` 与 `POST /api/pay/ali/notify`：异步通知入口骨架。

请求示例：

```bash
curl -X POST http://localhost:8080/api/pay/wx/native \
  -H 'Content-Type: application/json' \
  -d '{"subject":"测试订单","amountFen":1}'
```

## 下一步

1. 在 `application.yml` 中替换真实沙箱或商户配置。
2. 将 `PaymentLearningService` 中的参数草稿替换为 IJPay 的实际 API 调用。
3. 在 notify 接口中完成验签、金额校验与订单幂等更新。
