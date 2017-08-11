package com.kikebodi.tshirtapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.kikebodi.tshirtapp.apiconnection.ApiConnectionManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
        apiManager.getTshirtsFromAPI();

        signal.await(3, TimeUnit.SECONDS);
        Assert.assertTrue(ApiConnectionManager.successfullGetRequest);
    }
}
