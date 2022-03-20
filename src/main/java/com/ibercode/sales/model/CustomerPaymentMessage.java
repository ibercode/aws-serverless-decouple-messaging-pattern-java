package com.ibercode.sales.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.io.Serializable;

@DynamoDbBean
public class CustomerPaymentMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String saleId = null;
    private String paymentId = null;
    private String customerId = null;
    private String customerFullName = null;
    private String customerEmail = null;
    private String amount = null;
    private String product = null;

    public CustomerPaymentMessage() {
    }

    public CustomerPaymentMessage(String saleId, String paymentId, String customerId, String customerFullName, String customerEmail, String amount, String product) {
        this.saleId = saleId;
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.customerFullName = customerFullName;
        this.customerEmail = customerEmail;
        this.amount = amount;
        this.product = product;
    }

    @DynamoDbPartitionKey
    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}

