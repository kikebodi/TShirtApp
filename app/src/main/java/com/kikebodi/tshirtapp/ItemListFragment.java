package com.kikebodi.tshirtapp;



import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kikebodi.tshirtapp.apiconnection.models.Shirt;

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
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),recyclerView,new RecyclerItemClickListener.OnItemClickListener(){
            @Override public void onItemClick(View view, int position) {
                Log.d(TAG, "Clicked position "+position);
                try{
                    Shirt item = itemList.get(position);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.your_placeholder,new ShirtDetailFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }));
        mAdapter = new CustomAdapter(itemList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(),1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
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
}
