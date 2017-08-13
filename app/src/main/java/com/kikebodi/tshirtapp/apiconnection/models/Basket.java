package com.kikebodi.tshirtapp.apiconnection.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kike Bodi on 12/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class Basket {

    @SerializedName("shirts")
    @Expose
    private Shirt[] shirts;

    public Basket(List<Shirt> cart){
        this.shirts = new Shirt[cart.size()];
        for(int i=0;i<cart.size();i++){
            shirts[i] = cart.get(i);
        }
    }
}
