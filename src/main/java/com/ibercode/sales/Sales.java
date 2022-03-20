package com.ibercode.sales;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.ibercode.sales.utils.SalesDDBUtils;

public class Sales implements RequestHandler<SQSEvent, String> {

    private SalesDDBUtils ddbUtils = new SalesDDBUtils();

    @Override
    public String handleRequest(SQSEvent event, Context context) {

        String region = System.getenv("REGION");
        String tableName = System.getenv("SALES_TABLE");

        event.getRecords()
                .forEach(
                        m -> {
                            String record = m.getBody();
                            System.out.println("New record >" + record);
                            String saleId = ddbUtils.putItem(record, tableName);
                            System.out.println("[saleId] " + saleId);
                        });

        return "Done";
    }
}
