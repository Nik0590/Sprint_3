package com;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@Story("Creating courier")

public class CreateCourierTest extends FeatureTest {

    private CourierClient courierClient;
    private Courier courier;
    private int courierId;

    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courier = CourierGenerator.builder()
                .build().getRandom();
    }

    @After
    public void tearDown(){

        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Create new courier")
    @Description("Create new courier and then try to verify if he`s really has create")
    public void courierIsCreatedTrueTest(){
        ValidatableResponse isCreated = courierClient.create(courier);
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));
        courierId = response.extract().path("id");
        boolean actual = isCreated.extract().path("ok");

        assertNotEquals(0, courierId);
        assertTrue(actual);
    }

    @Test
    @DisplayName("Create new courier")
    @Description("Create new courier and then try to verify status code")
    public void courierIsCreatedStatusCodeTest(){
        ValidatableResponse isCreated = courierClient.create(courier);
        Integer statusCode = isCreated.extract().statusCode();
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));
        courierId = response.extract().path("id");

        assertThat("Courier cannot created", statusCode, equalTo(SC_CREATED));
        assertNotEquals(0, courierId);
    }
    @Test
    @DisplayName("Duplicate of new courier")
    @Description("Create new courier and then create the same courier")
    public void duplicateCourierFalseTest(){
        ValidatableResponse firstCourierIsCreated = courierClient.create(courier);
        Integer statusCode = firstCourierIsCreated.extract().statusCode();
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));
        courierId = response.extract().path("id");
        ValidatableResponse duplicateCourierIsNotCreated = courierClient.create(courier);
        Integer duplicateStatusCode = duplicateCourierIsNotCreated.extract().statusCode();

        assertNotEquals("Courier cannot created", statusCode, equalTo(SC_CREATED));
        assertThat("This login is not in use", duplicateStatusCode ,equalTo(SC_CONFLICT));
    }

    @Test
    @DisplayName("Duplicate new courier error message")
    @Description("Create new courier and then create the same courier")
    public void duplicateCourierErrorMessageTest(){
        courierClient.create(courier);
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));
        courierId = response.extract().path("id");
        ValidatableResponse duplicateCourierIsNotCreated = courierClient.create(courier);
        String actualErrorMessage = "Этот логин уже используется. Попробуйте другой.";
        String expectErrorMessage = duplicateCourierIsNotCreated.extract().path("message");

        assertEquals(expectErrorMessage, actualErrorMessage);
    }
}
