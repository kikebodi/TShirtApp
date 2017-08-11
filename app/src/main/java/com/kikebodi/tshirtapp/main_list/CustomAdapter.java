package com.kikebodi.tshirtapp.main_list;

/**
 * Created by Kike Bodi on 11/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kikebodi.tshirtapp.R;
import com.kikebodi.tshirtapp.apiconnection.models.Shirt;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Shirt> itemsList;
    private ImageLoader imageLoader = ImageLoader.getInstance();


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, color;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            price = view.findViewById(R.id.price);
            image = view.findViewById(R.id.imageView);
        }
    }

    public CustomAdapter(List<Shirt> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shirt_card_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Shirt item = itemsList.get(position);
        holder.title.setText(item.getName());
        holder.price.setText(String.format("$%s", String.valueOf(item.getPrice())));
        imageLoader.displayImage(item.getPicture(), holder.image);

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}