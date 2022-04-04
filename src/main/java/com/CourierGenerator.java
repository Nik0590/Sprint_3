package com;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourierGenerator {
    @Step("Create random courier")
    public Courier getRandom(){
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);

        Allure.addAttachment("login", login);
        Allure.addAttachment("Password", password);
        Allure.addAttachment("FirstName", firstName);

        return new Courier(login, password, firstName);
    }

}
