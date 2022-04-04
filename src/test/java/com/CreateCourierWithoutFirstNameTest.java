package com;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


@Story("Creating courier")
public class CreateCourierWithoutFirstNameTest extends FeatureTest {

    private CourierClient courierClient;
    private CourierWithoutFirstName courierWithoutFirstName;

    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courierWithoutFirstName = CourierWithoutFirstName.builder()
                .login(RandomStringUtils.randomAlphabetic(10))
                .password(RandomStringUtils.randomAlphabetic(10))
                .build();
    }

    @Test
    @DisplayName("All fields are mandatory")
    @Description("Use only firstName and password to create courier")
    public void createCourierWithoutFirstName(){

        ValidatableResponse courierIsNotCreated = courierClient.createCourierWithoutFirstName(courierWithoutFirstName);
        Integer statusCode = courierIsNotCreated.extract().statusCode();
        String actualErrorMessage = "Недостаточно данных для создания учетной записи";
        String expectErrorMessage = courierIsNotCreated.extract().path("message");

        assertThat("Courier cannot created", statusCode, equalTo(SC_BAD_REQUEST));
        assertEquals(expectErrorMessage, actualErrorMessage);
    }
}
