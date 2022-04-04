package com;

public class CourierCredentialsWithoutPassword {

    private final String login;


    public CourierCredentialsWithoutPassword(String login) {

        this.login =login;
    }

    public static CourierCredentialsWithoutPassword from(Courier courier) {
        return new CourierCredentialsWithoutPassword(courier.getLogin());
    }

    public String getLogin(){
        return login;
    }
}
