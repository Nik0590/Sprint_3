package com;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient extends ScooterRestClient {

    private static final  String COURIER_PATH = "api/v1/courier/";

    @Step("Login with credentials {credetials}")
    public ValidatableResponse loginWithoutLogin(CourierCredentialsWithoutLogin courierCredentialsWithoutLogin){
        return given().log().all()
                .spec(getBaseSpec())
                .body(courierCredentialsWithoutLogin)
                .when()
                .post(COURIER_PATH + "login")
                .then().log().all();
    }

    @Step("Login with credentials {credetials}")
    public ValidatableResponse loginWithoutPassword(CourierCredentialsWithoutPassword courierCredentialsWithoutPassword){
        return given().log().all()
                .spec(getBaseSpec())
                .body(courierCredentialsWithoutPassword)
                .when()
                .post(COURIER_PATH + "login")
                .then().log().all();
    }

    @Step("Login with credentials {credetials}")
    public ValidatableResponse login(CourierCredentials credentials){
        return given().log().all()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(COURIER_PATH + "login")
                .then().log().all();
    }
    @Step("Create Courier")
    public ValidatableResponse create(Courier courier){
        return given().log().all()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Create CourierWithoutLogin")
    public ValidatableResponse createCourierWithoutLogin(CourierWithoutLogin courierWithoutLogin){
        return given().log().all()
                .spec(getBaseSpec())
                .body(courierWithoutLogin)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Create CourierWithoutPassword")
    public ValidatableResponse createCourierWithoutPassword(CourierWithoutPassword CourierWithoutPassword){
        return given().log().all()
                .spec(getBaseSpec())
                .body(CourierWithoutPassword)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Create CourierWithoutFirstName")
    public ValidatableResponse createCourierWithoutFirstName(CourierWithoutFirstName CourierWithoutFirstName){
        return given().log().all()
                .spec(getBaseSpec())
                .body(CourierWithoutFirstName)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Delete Courier {courierId}")
    public ValidatableResponse delete(int courierId){
        return given().log().all()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + courierId)
                .then().log().all();
    }
}
