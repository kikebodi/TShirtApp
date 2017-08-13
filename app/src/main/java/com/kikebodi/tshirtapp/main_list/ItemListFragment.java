package com.kikebodi.tshirtapp.main_list;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kikebodi.tshirtapp.MainActivity;
import com.kikebodi.tshirtapp.R;
import com.kikebodi.tshirtapp.apiconnection.models.Shirt;
import com.kikebodi.tshirtapp.details.ShirtDetailFragment;
import com.kikebodi.tshirtapp.shopingcart.ShoppingCartFragment;

import java.util.ArrayList;
import java.util.List;

public class ItemListFragment extends Fragment {

    private static final String TAG = ItemListFragment.class.getName();

    private List<Shirt> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CustomAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mAdapter = new CustomAdapter(itemList, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(),1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        //In case of back key pressed, add shopping cart icon
        ((MainActivity)getActivity()).setShoppingCartIconVisibility(true);
    }

    public void prepareItems(List<Shirt> list){
        if(list == null) return;

        for (Shirt element : list) {
            if(element.getQuantity() > 0){
                itemList.add(element);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    public void changeToDetailsFragment(int position) {
        Shirt item = itemList.get(position);
        ShirtDetailFragment newFragment = new ShirtDetailFragment();
        newFragment.setShirt(item);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.placeholder,newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void changeToCartFragment() {
        ShoppingCartFragment newFragment = new ShoppingCartFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.placeholder,newFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void showCheckoutSnackbar(){
        Snackbar snackbar = Snackbar
                .make(recyclerView, "Shirt added to the cart", Snackbar.LENGTH_LONG)
                .setAction("Checkout", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //go to shopping cart fragment
                        changeToCartFragment();
                    }
                });

        snackbar.show();
    }
}
