package com.kikebodi.tshirtapp.apiconnection;

import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kikebodi.tshirtapp.apiconnection.models.Basket;
import com.kikebodi.tshirtapp.apiconnection.models.CreateOrder;
import com.kikebodi.tshirtapp.apiconnection.models.Shirt;
import com.kikebodi.tshirtapp.main_list.ItemListFragment;
import com.kikebodi.tshirtapp.shopingcart.ShoppingCartFragment;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
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

    private static final String URL = "https://mock-shirt-backend.getsandbox.com/";
    private static final String TAG = ApiConnectionManager.class.getName();
    private final SandboxApi sandboxApi;

    //Test driven variables
    public static boolean successfullGetRequest  = false;
    public static boolean successfullPostRequest  = false;

    public ApiConnectionManager(){
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


    public void getTshirtsFromAPI(final Fragment myFragment){
        Call<List<Shirt>> call = sandboxApi.getTshirts();
        call.enqueue(new Callback<List<Shirt>>() {
            @Override
            public void onResponse(Call<List<Shirt>> call, Response<List<Shirt>> response) {
                Log.d(TAG,"onSuccess");
                successfullGetRequest = true;
                int statusCode = response.code();
                successfullGetRequest = (statusCode == 200);
                if(statusCode == 500){
                    //Perform request again in 200ms
                    Handler mHandler = new Handler(Looper.getMainLooper());
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getTshirtsFromAPI(myFragment);
                        }
                    },200);
                }

                List<Shirt> shirts = response.body();
                if(myFragment instanceof  ItemListFragment){
                    //Populate the recyclerview
                    ((ItemListFragment)myFragment).prepareItems(shirts);
                }

            }

            @Override
            public void onFailure(Call<List<Shirt>> call, Throwable t) {
                Log.d(TAG,"onFailure GET");
                successfullGetRequest = false;
                t.printStackTrace();
            }
        });
    }

    public void postOrderToAPI(CreateOrder createOrder, final Fragment myFragment){
        Call<ResponseBody> response = sandboxApi.postOrder(createOrder);
        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                successfullPostRequest = true;
                if(response.code() == 200 && myFragment instanceof ShoppingCartFragment){
                    ((ShoppingCartFragment) myFragment).showToastOrderConfirmed();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"onFailure POST");
                successfullPostRequest = false;
                t.printStackTrace();
            }
        });
    }
}
