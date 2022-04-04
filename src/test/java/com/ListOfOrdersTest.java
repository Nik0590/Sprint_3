package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Story("Create order")

public class ListOfOrdersTest extends FeatureTest {

    private OrderClient orderClient;
    private String[] color;
    private Order order;
    private int orderId;

    @Before
    public void setUp(){
        orderClient = new OrderClient();
        order = OrderGenerator.builder()
                .build().getOrder(color);
    }

    @After
    public void tearDown(){

        orderClient.cancelOrder(orderId);
    }

    @Test
    @DisplayName("Track new order")
    @Description("Create new order and then try to track it")
    public void orderIsCreateTrueTest(){
        ValidatableResponse isCreated = orderClient.createOrder(order);
        orderId = isCreated.extract().path("track");
        ValidatableResponse isTracked = orderClient.getListOfOrders();
        int actual = isTracked.extract().statusCode();

        OrderCredentials credentials = OrderCredentials.from(order);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String actualJson = gson.toJson(credentials);
        String expectJson = String.valueOf(credentials);

        assertEquals(200, actual);
        assertNotEquals(0, orderId);
        assertEquals(expectJson, actualJson);
    }
}