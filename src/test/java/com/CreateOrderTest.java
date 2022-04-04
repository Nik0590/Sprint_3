package com;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Story("Create order")
@RunWith(Parameterized.class)

public class CreateOrderTest extends FeatureTest {

    private final String[] color;
    private final int expected;
    private OrderClient orderClient;
    private Order order;
    private int orderId;

    public CreateOrderTest(String[] color, int expected){
        this.color = color;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index} actualColor:{0}")
    public static Object[][] getColorData(){
        return new Object[][]{
                {new String[]{"BLACK"}, 201},
                {new String[]{"GREY"}, 201},
                {new String[]{"BLACK", "GREY"}, 201},
                {new String[]{""}, 201}
        };
    }
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
    @DisplayName("Create new order")
    @Description("Create new order and then try to verify it")
    public void orderIsCreateTrueTest(){
        ValidatableResponse isCreated = orderClient.createOrder(order);
        orderId = isCreated.extract().path("track");
        int actual = isCreated.extract().statusCode();

        assertEquals(expected, actual);
        assertNotEquals(0, orderId);
    }
}