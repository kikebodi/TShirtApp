package com.kikebodi.tshirtapp;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.kikebodi.tshirtapp.apiconnection.ApiConnectionManager;
import com.kikebodi.tshirtapp.apiconnection.models.Basket;
import com.kikebodi.tshirtapp.apiconnection.models.CreateOrder;
import com.kikebodi.tshirtapp.apiconnection.models.Shirt;
import com.kikebodi.tshirtapp.shopingcart.ShoppingCart;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kike Bodi on 11/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class ApiTest {

    private Context instrumentationContext;
    final CountDownLatch signal = new CountDownLatch(1);

    @Before
    public void setUp() {
        instrumentationContext = InstrumentationRegistry.getContext();
    }

    @Test
    public void testGET() throws InterruptedException {
        ApiConnectionManager apiManager = new ApiConnectionManager();
        apiManager.getTshirtsFromAPI(new Fragment());

        signal.await(3, TimeUnit.SECONDS);
        Assert.assertTrue(ApiConnectionManager.successfullGetRequest);
    }

    @Test
    public void testPost() throws InterruptedException {
        ApiConnectionManager apiManager = new ApiConnectionManager();
        Shirt one = new Shirt(1,"one",10,"Black",1,"M","...");
        Shirt two = new Shirt(1,"two",10,"Black",1,"M","...");
        List<Shirt> list = new ArrayList<>();
        list.add(one);
        list.add(two);
        Basket basket = new Basket(list);
        CreateOrder createOrder = new CreateOrder(20,basket);
        apiManager.postOrderToAPI(createOrder, new Fragment());

        signal.await(3, TimeUnit.SECONDS);
        Assert.assertTrue(ApiConnectionManager.successfullPostRequest);
    }
}
