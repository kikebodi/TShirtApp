package com.kikebodi.tshirtapp;

/**
 * Created by Kike Bodi on 11/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kikebodi.tshirtapp.apiconnection.models.Tshirt;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Tshirt> itemsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, color;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            price = (TextView) view.findViewById(R.id.price);
            color = (TextView) view.findViewById(R.id.color);
        }
    }

    public CustomAdapter(List<Tshirt> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shirt_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tshirt item = itemsList.get(position);
        holder.title.setText(item.getName());
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.color.setText(item.getColour());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}