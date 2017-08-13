package com.kikebodi.tshirtapp.shopingcart;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kikebodi.tshirtapp.apiconnection.models.Shirt;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Kike Bodi on 12/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class ShoppingCart {

    private static final String SHOPPING_CART = "MyShoppingCartList";
    private static final String TAG = ShoppingCart.class.getName();

    public static boolean addToShoppingCart(Shirt item, Activity activity) {
        SharedPreferences mPrefs = activity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = mPrefs.getString(SHOPPING_CART, "");
        Type listType = new TypeToken<ArrayList<Shirt>>(){}.getType();
        List<Shirt> myList = (List<Shirt>) gson.fromJson(json, listType);
        myList = updateList(myList,item);
        json = gson.toJson(myList);
        prefsEditor.putString(SHOPPING_CART, json);
        prefsEditor.apply();
        return true;
    }

    public static void updateShoppingCartList(List<Shirt> myList, Activity activity){
        SharedPreferences mPrefs = activity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myList);
        prefsEditor.putString(SHOPPING_CART, json);
        prefsEditor.apply();
    }

    private static List<Shirt> updateList(List<Shirt> myList, Shirt item) {
        if(myList == null){
            myList = new ArrayList<>();
        }
        myList.add(item);
        return myList;
    }

    public static List<Shirt> getShoppingCartItems(Activity activity) {
        SharedPreferences mPrefs = activity.getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString(SHOPPING_CART, "");
        Type listType = new TypeToken<ArrayList<Shirt>>(){}.getType();
        List<Shirt> myList = (List<Shirt>) gson.fromJson(json, listType);
        Log.d(TAG, "Items in the list: "+myList.size());
        return myList;
    }
}
