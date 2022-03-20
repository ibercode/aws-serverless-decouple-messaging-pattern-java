package com.ibercode.customer.model;

public class CustomerResponse {

    private String message;
    private String paymentId;

    public CustomerResponse(String message, String paymentId) {
        this.message = message;
        this.paymentId = paymentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
