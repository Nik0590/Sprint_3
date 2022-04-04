package com;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderGenerator {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private String rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    @Step("Create order")
    public Order getOrder(String[] color){
        String firstName = "Naruto";
        String lastName = "Uchiha";
        String address = "Konoha, 142 apt.";
        String metroStation = "4";
        String phone = "+7 800 355 35 35";
        String rentTime = "5";
        String deliveryDate = "2022-04-06";
        String comment = "Saske, come back to Konoha";
        this.color = color;

        Allure.addAttachment("firstName", firstName);
        Allure.addAttachment("lastName", lastName);
        Allure.addAttachment("metroStation", metroStation);
        Allure.addAttachment("phone", phone);
        Allure.addAttachment("rentTime", rentTime);
        Allure.addAttachment("deliveryDate", deliveryDate);
        Allure.addAttachment("comment", comment);
        Allure.addAttachment("color", String.valueOf(color));

        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);

    }

}