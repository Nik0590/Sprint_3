package com;

public class CourierCredentialsWithoutLogin {

    private final String password;


    public CourierCredentialsWithoutLogin(String password) {

        this.password =password;
    }

    public static CourierCredentialsWithoutLogin from(Courier courier) {
        return new CourierCredentialsWithoutLogin(courier.getPassword());
    }

    public String getPassword(){
        return password;
    }
}
