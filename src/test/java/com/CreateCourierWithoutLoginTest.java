package com;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


@Story("Creating courier")
public class CreateCourierWithoutLoginTest extends FeatureTest {

    private CourierClient courierClient;
    private CourierWithoutLogin courierWithoutLogin;

    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courierWithoutLogin = CourierWithoutLogin.builder()
                .firstName(RandomStringUtils.randomAlphabetic(10))
                .password(RandomStringUtils.randomAlphabetic(10))
                .build();
    }

    @Test
    @DisplayName("All fields are mandatory")
    @Description("Use only firstName and password to create courier")
    public void createCourierWithoutLogin(){

        ValidatableResponse courierIsNotCreated = courierClient.createCourierWithoutLogin(courierWithoutLogin);
        Integer statusCode = courierIsNotCreated.extract().statusCode();
        String actualErrorMessage = "Недостаточно данных для создания учетной записи";
        String expectErrorMessage = courierIsNotCreated.extract().path("message");

        assertThat("Courier cannot created", statusCode, equalTo(SC_BAD_REQUEST));
        assertEquals(expectErrorMessage, actualErrorMessage);
    }
}
