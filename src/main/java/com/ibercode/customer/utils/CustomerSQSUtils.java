package com.ibercode.customer.utils;

import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

public class CustomerSQSUtils {

    public String sendSQSMessage(String body, String queueUrl) {

        SqsClient sqsClient = SqsClient.builder()
                .region(Region.of(System.getenv(SdkSystemSetting.AWS_REGION.environmentVariable())))
                .build();

        SendMessageResponse response = sqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(body)
                .delaySeconds(10)
                .build());

        return response.messageId();
    }
}
