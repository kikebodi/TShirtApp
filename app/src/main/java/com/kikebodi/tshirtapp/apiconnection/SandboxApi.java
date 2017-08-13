package com.kikebodi.tshirtapp.apiconnection;

import com.kikebodi.tshirtapp.apiconnection.models.CreateOrder;
import com.kikebodi.tshirtapp.apiconnection.models.Shirt;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Kike Bodi on 11/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

interface SandboxApi {

    @GET("shirts")
    Call<List<Shirt>> getTshirts();

    @Headers("Content-Type: application/json")
    @POST("order")
    Call<ResponseBody> postOrder(@Body CreateOrder createOrder);
}
