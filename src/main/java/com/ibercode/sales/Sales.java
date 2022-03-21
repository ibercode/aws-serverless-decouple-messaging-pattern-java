package com.ibercode.sales;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.ibercode.sales.utils.SalesDDBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sales implements RequestHandler<SQSEvent, String> {

    private final SalesDDBUtils ddbUtils = new SalesDDBUtils();
    private static final Logger LOGGER = LoggerFactory.getLogger(Sales.class);

    @Override
    public String handleRequest(SQSEvent event, Context context) {

        String tableName = System.getenv("SALES_TABLE");

        event.getRecords()
                .forEach(
                        m -> {
                            String record = m.getBody();
                            LOGGER.info("[record]" + record);
                            String saleId = ddbUtils.putItem(record, tableName);
                            LOGGER.info("[saleId] " + saleId);
                        });
        return "Done";
    }
}
