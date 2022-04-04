package com;

public class OrderCredentials {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;





    public OrderCredentials(String firstName, String lastName, String address, String metroStation, String phone, String comment, String[] color, String rentTime, String deliveryDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.comment = comment;
        this.color = color;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
    }

    public static OrderCredentials from(Order order) {
        return new OrderCredentials(order.getFirstName(),
                order.getLastName(),
                order.getAddress(),
                order.getMetroStation(),
                order.getPhone(),
                order.getComment(),
                order.getColor(),
                order.getRentTime(),
                order.getDeliveryDate());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {

        return address;
    }

    public String getMetroStation() {

        return metroStation;
    }

    public String getPhone() {

        return phone;
    }

    public String getComment() {

        return comment;
    }

    public String[] getColor() {

        return color;
    }

    public String getRentTime() {

        return rentTime;
    }

    public String getDeliveryDate() {

        return deliveryDate;
    }
}