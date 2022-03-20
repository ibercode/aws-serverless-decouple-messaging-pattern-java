package com.ibercode.customer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.ibercode.customer.model.CustomerResponse;
import com.ibercode.customer.utils.CustomerDDBUtils;
import com.ibercode.customer.utils.CustomerSQSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customer implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final Gson gson = new Gson();
    private final Logger logger = LoggerFactory.getLogger(Customer.class);
    private final CustomerDDBUtils ddbUtils = new CustomerDDBUtils();
    private final CustomerSQSUtils sqsUtils = new CustomerSQSUtils();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {

        String TABLE_NAME = System.getenv("CUSTOMERS_TABLE");
        String QUEUE_URL = System.getenv("QUEUE_URL");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        String paymentId = ddbUtils.updateCustomerDDB(event.getBody(), TABLE_NAME);
        String sqsId = sqsUtils.sendSQSMessage(event.getBody(), QUEUE_URL);

        logger.info("[paymentId]" + paymentId);
        logger.info("[sqsId]" + sqsId);

        CustomerResponse customerResponse = new CustomerResponse("Payment registered! ", paymentId);

        response.setBody(gson.toJson(customerResponse));

        return response;
    }
}
