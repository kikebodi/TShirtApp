package com.kikebodi.tshirtapp.apiconnection;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kikebodi.tshirtapp.MainActivity;
import com.kikebodi.tshirtapp.apiconnection.models.Tshirt;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kike Bodi on 11/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class ApiConnectionManager {

    public static final String URL = "https://mock-shirt-backend.getsandbox.com/";
    private static final String TAG = ApiConnectionManager.class.getName();
    private final SandboxApi sandboxApi;
    private MainActivity mainActivity;

    //Test driven variables
    public static boolean successfullGetRequest  = false;

    public ApiConnectionManager(MainActivity mActivity){
        this.mainActivity = mActivity;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client.build())
                .build();

        this.sandboxApi = retrofit.create(SandboxApi.class);
    }


    public void getTshirtsFromAPI(){
        Call<List<Tshirt>> call = sandboxApi.getTshirts();
        call.enqueue(new Callback<List<Tshirt>>() {
            @Override
            public void onResponse(Call<List<Tshirt>> call, Response<List<Tshirt>> response) {
                Log.d(TAG,"onSuccess");
                successfullGetRequest = true;
                int statusCode = response.code();
                successfullGetRequest = (statusCode == 200);

                List<Tshirt> tshirts = response.body();
                mainActivity.prepareItems(tshirts);
            }

            @Override
            public void onFailure(Call<List<Tshirt>> call, Throwable t) {
                Log.d(TAG,"onFailure GET");
                successfullGetRequest = false;
                t.printStackTrace();
            }
        });
    }
}
