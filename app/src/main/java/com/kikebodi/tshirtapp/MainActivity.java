package com.kikebodi.tshirtapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kikebodi.tshirtapp.apiconnection.ApiConnectionManager;
import com.kikebodi.tshirtapp.apiconnection.models.Tshirt;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Tshirt> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new CustomAdapter(itemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        
        //prepareItems();
        ApiConnectionManager apiManager = new ApiConnectionManager(this);
        apiManager.getTshirtsFromAPI();
    }

    private void prepareItems() {

        Tshirt one = new Tshirt(999999,"Normal t", 88, "Black",1,"M","url");
        Tshirt two = new Tshirt(999999,"Normal 222", 88, "Black",1,"M","url");
        Tshirt three = new Tshirt(999999,"Normal 333", 88, "Black",1,"M","url");
        itemList.add(one);
        itemList.add(two);
        itemList.add(three);
        mAdapter.notifyDataSetChanged();
    }

    public void prepareItems(List<Tshirt> list){
        for (Tshirt element : list) {
            itemList.add(element);
        }
        mAdapter.notifyDataSetChanged();
    }
}
