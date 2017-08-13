package com.kikebodi.tshirtapp.shopingcart;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kikebodi.tshirtapp.R;
import com.kikebodi.tshirtapp.apiconnection.ApiConnectionManager;
import com.kikebodi.tshirtapp.apiconnection.models.Basket;
import com.kikebodi.tshirtapp.apiconnection.models.CreateOrder;
import com.kikebodi.tshirtapp.apiconnection.models.Shirt;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Kike Bodi on 12/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class ShoppingCartFragment extends Fragment{

    private RecyclerView recyclerView;
    private ItemCartAdapter mAdapter;
    private TextView total;
    private Button payButton;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private ShoppingCartFragment thisFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.thisFragment = this;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        total = view.findViewById(R.id.total);
        payButton = view.findViewById(R.id.button);
        mAdapter = new ItemCartAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(),1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Shirt> myList = ShoppingCart.getShoppingCartItems(getActivity());
                Basket basket = new Basket(myList);
                ApiConnectionManager apiManager = new ApiConnectionManager();
                CreateOrder createOrder = new CreateOrder(mAdapter.getTotalPrice(),basket);
                apiManager.postOrderToAPI(createOrder, thisFragment);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void updateTotal(long total){
        this.total.setText(String.format("$%s", String.valueOf(total)));
    }

    public void showToastOrderConfirmed(){
        Toast.makeText(getActivity().getApplicationContext(),"Order confirmed", Toast.LENGTH_LONG).show();
    }
}
