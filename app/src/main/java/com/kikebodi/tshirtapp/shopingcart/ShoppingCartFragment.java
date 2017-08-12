package com.kikebodi.tshirtapp.shopingcart;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kikebodi.tshirtapp.R;
import com.kikebodi.tshirtapp.apiconnection.models.Shirt;
import com.kikebodi.tshirtapp.main_list.CustomAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Kike Bodi on 12/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class ShoppingCartFragment extends Fragment{

    List<Shirt> itemList;
    private RecyclerView recyclerView;
    private ItemCartAdapter mAdapter;
    private TextView total;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        total = view.findViewById(R.id.total);
        mAdapter = new ItemCartAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(),1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*        imageLoader.displayImage(item.getPicture(), image);
        title.setText(item.getName());
        price.setText(String.format("$%s", String.valueOf(item.getPrice())));
        size.setText(item.getSize().toUpperCase());*/
    }

    public void updateTotal(long total){
        this.total.setText(String.format("$%s", String.valueOf(total)));
    }
}
