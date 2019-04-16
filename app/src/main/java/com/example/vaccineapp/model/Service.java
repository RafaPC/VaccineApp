package com.example.vaccineapp.model;

public class Service {

    private String serviceName;
    private int price;
    private String coin;

    public Service(String serviceName, int price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getServiceName() {
        return serviceName;
    }
}
