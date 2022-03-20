package com.ibercode.sales.utils;

import com.google.gson.Gson;
import com.ibercode.sales.model.CustomerPaymentMessage;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.SdkSystemSetting;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.UUID;

public class SalesDDBUtils {

    private final Gson gson = new Gson();
    private final DynamoDbClient ddb = DynamoDbClient.builder()
            .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
            .region(Region.of(System.getenv(SdkSystemSetting.AWS_REGION.environmentVariable())))
            .build();
    private final DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(ddb)
            .build();

    public String putItem(String body, String tableName){

        DynamoDbTable<CustomerPaymentMessage> mappedTable = enhancedClient
                .table(tableName, TableSchema.fromBean(CustomerPaymentMessage.class));

        CustomerPaymentMessage payment = gson.fromJson(body, CustomerPaymentMessage.class);
        String saleId = UUID.randomUUID().toString();
        payment.setSaleId(saleId);

        mappedTable.putItem(payment);
        return saleId;
    }
}
