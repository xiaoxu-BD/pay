package com.example.pay.model;

public class CreateOrderRequest {
    private String subject = "IJPay 学习订单";
    private int amountFen = 1;

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public int getAmountFen() { return amountFen; }
    public void setAmountFen(int amountFen) { this.amountFen = amountFen; }
}
