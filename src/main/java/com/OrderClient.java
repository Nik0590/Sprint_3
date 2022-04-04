package com;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends ScooterRestClient {

    private static final  String COURIER_PATH = "api/v1/orders/";


    @Step("Create Order")
    public ValidatableResponse createOrder(Order order){
        return given().log().all()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Create CourierWithoutLogin")
    public ValidatableResponse getListOfOrders(){
        return given().log().all()
                .spec(getBaseSpec())
                .when()
                .get(COURIER_PATH)
                .then().log().all();
    }

    @Step("Delete Courier {courierId}")
    public ValidatableResponse cancelOrder(int orderId){
        return given().log().all()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + "cancel/" + orderId)
                .then().log().all();
    }
}
