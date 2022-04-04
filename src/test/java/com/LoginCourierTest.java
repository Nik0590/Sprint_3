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

@Story("Login courier")

public class LoginCourierTest extends FeatureTest {

    private CourierClient courierClient;
    private Courier courier;
    private int courierId;

    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courier = CourierGenerator.builder()
                .build().getRandom();
        courierClient.create(courier);
    }

    @After
    public void tearDown(){

        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Login new courier")
    @Description("Login new courier and then try to verify it")
    public void courierIsLoginTrueTest(){
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));
        courierId = response.extract().path("id");

        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Check status code of new login courier")
    @Description("Login new courier and then try to verify status code")
    public void courierIsLoginStatusCodeTest(){
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));
        Integer statusCode = response.extract().statusCode();
        courierId = response.extract().path("id");

        assertThat("Courier cannot login", statusCode, equalTo(SC_OK));
        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Verify all mandatory fields")
    @Description("Login courier without field login")
    public void courierIsNotLoginWithoutLoginTest(){
        ValidatableResponse responseWithLogin = courierClient.loginWithoutLogin(CourierCredentialsWithoutLogin.from(courier));
        Integer statusCode = responseWithLogin.extract().statusCode();
        String actual = "Недостаточно данных для входа";
        String expect = responseWithLogin.extract().path("message");
        ValidatableResponse response = courierClient.login(CourierCredentials.from(courier));
        courierId = response.extract().path("id");

        assertEquals(expect, actual);
        assertThat("Courier can login", statusCode, equalTo(SC_BAD_REQUEST));
        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Verify all mandatory fields")
    @Description("Login new courier without field password")
    public void courierIsNotLoginWithoutPasswordTest(){
        ValidatableResponse responseWithPassword = courierClient.loginWithoutPassword(CourierCredentialsWithoutPassword.from(courier));
        Integer statusCode = responseWithPassword.extract().statusCode();
        String actual = "Недостаточно данных для входа";
        String expect = responseWithPassword.extract().path("message");
        ValidatableResponse responseTrueLogin = courierClient.login(CourierCredentials.from(courier));
        courierId = responseTrueLogin.extract().path("id");

        assertEquals(expect, actual);
        assertThat("Courier can login", statusCode, equalTo(SC_BAD_REQUEST));
        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Login with invalid login")
    @Description("Login new courier with invalid login")
    public void courierIsNotLoginWithInvalidLoginTest(){
        ValidatableResponse response = courierClient.login(new CourierCredentials("1234567", courier.getPassword()));
        Integer statusCode = response.extract().statusCode();
        String expect = "Учетная запись не найдена";
        String actual = response.extract().path("message");
        ValidatableResponse responseTrueLogin = courierClient.login(CourierCredentials.from(courier));
        courierId = responseTrueLogin.extract().path("id");

        assertEquals(expect, actual);
        assertThat("Courier can login", statusCode, equalTo(SC_NOT_FOUND));
        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Login with invalid password")
    @Description("Login new courier with invalid password")
    public void courierIsNotLoginWithInvalidPasswordTest(){
        ValidatableResponse response = courierClient.login(new CourierCredentials(courier.getLogin(), "1234567"));
        Integer statusCode = response.extract().statusCode();
        String expect = "Учетная запись не найдена";
        String actual = response.extract().path("message");
        ValidatableResponse responseTrueLogin = courierClient.login(CourierCredentials.from(courier));
        courierId = responseTrueLogin.extract().path("id");

        assertEquals(expect, actual);
        assertThat("Courier can login", statusCode, equalTo(SC_NOT_FOUND));
        assertNotEquals(0, courierId);
    }

    @Test
    @DisplayName("Login with invalid login and password")
    @Description("Login new courier with invalid login and password")
    public void courierIsNotLoginWithInvalidLoginAndPasswordTest(){
        ValidatableResponse response = courierClient.login(new CourierCredentials("1234567", "1234567"));
        Integer statusCode = response.extract().statusCode();
        String expect = "Учетная запись не найдена";
        String actual = response.extract().path("message");
        ValidatableResponse responseTrueLogin = courierClient.login(CourierCredentials.from(courier));
        courierId = responseTrueLogin.extract().path("id");

        assertEquals(expect, actual);
        assertThat("Courier can login", statusCode, equalTo(SC_NOT_FOUND));
        assertNotEquals(0, courierId);
    }
}