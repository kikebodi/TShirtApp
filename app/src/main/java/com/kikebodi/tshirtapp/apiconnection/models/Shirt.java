package com.kikebodi.tshirtapp.apiconnection.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kike Bodi on 11/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class Shirt {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private long price;
    @SerializedName("colour")
    @Expose
    private String colour;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("picture")
    @Expose
    private String picture;

    /**
     * No args constructor for use in serialization
     */
    public Shirt(){
    }

    public Shirt(long id, String name, long price, String colour, int quantity, String size, String picture){
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.colour = colour;
        this.quantity = quantity;
        this.size = size;
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String getColour() {
        return colour;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSize() {
        return size;
    }

    public String getPicture() {
        return picture;
    }
}
