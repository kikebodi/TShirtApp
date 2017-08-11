package com.kikebodi.tshirtapp.apiconnection;

import com.kikebodi.tshirtapp.apiconnection.models.Shirt;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Kike Bodi on 11/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public interface SandboxApi {

    @GET("shirts")
    Call<List<Shirt>> getTshirts();
}
