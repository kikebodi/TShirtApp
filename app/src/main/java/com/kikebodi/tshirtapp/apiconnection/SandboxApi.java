package com.kikebodi.tshirtapp.apiconnection;

import com.kikebodi.tshirtapp.apiconnection.models.Tshirt;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Kike Bodi on 11/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public interface SandboxApi {

    @GET("shirts")
    Call<List<Tshirt>> getTshirts();
}
