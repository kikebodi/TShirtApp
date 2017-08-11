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
import android.widget.ImageView;
import android.widget.TextView;

import com.kikebodi.tshirtapp.apiconnection.models.Shirt;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Shirt> itemsList;
    private ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, color;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            price = (TextView) view.findViewById(R.id.price);
            image = (ImageView) view.findViewById(R.id.imageView);
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
        holder.price.setText("$"+String.valueOf(item.getPrice()));
        imageLoader.displayImage(item.getPicture(), holder.image);

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}