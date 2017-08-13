package com.kikebodi.tshirtapp.apiconnection.models;

/**
 * Created by Kike Bodi on 13/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class CreateOrder {

    private long total;
    private Basket basket;

    public CreateOrder(long total, Basket basket){
        this.total = total;
        this.basket = basket;
    }
}
