package com.kikebodi.tshirtapp.shopingcart;

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

/**
 * Created by Kike Bodi on 12/08/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class ItemCartAdapter extends RecyclerView.Adapter<ItemCartAdapter.MyViewHolder>{

    public static final String TAG = ItemCartAdapter.class.getName();

    private List<Shirt> itemsList;
    private ShoppingCartFragment parentFragment;
    private ImageLoader imageLoader = ImageLoader.getInstance();


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price;
        public ImageView image, delete;

        public MyViewHolder(View view) {

            super(view);
            title = view.findViewById(R.id.title);
            price = view.findViewById(R.id.price);
            image = view.findViewById(R.id.image);
            delete = view.findViewById(R.id.delete);
        }
    }

    public ItemCartAdapter(ShoppingCartFragment parentFragment) {
        this.itemsList = ShoppingCart.getShoppingCartItems(parentFragment.getActivity());
        this.parentFragment = parentFragment;
        parentFragment.updateTotal(getTotalPrice());
    }

    @Override
    public ItemCartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_cart_item, parent, false);
        return new ItemCartAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemCartAdapter.MyViewHolder holder, final int position) {
        final Shirt item = itemsList.get(position);
        holder.title.setText(item.getName());
        holder.price.setText(String.format("$%s", String.valueOf(item.getPrice())));
        imageLoader.displayImage(item.getPicture(), holder.image);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemsList.remove(position);
                ShoppingCart.updateShoppingCartList(itemsList,parentFragment.getActivity());
                parentFragment.updateTotal(getTotalPrice());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public long getTotalPrice() {
        long total = 0;
        if(itemsList == null) return 0;

        for(Shirt element : itemsList)
            total += element.getPrice();

        return total;
    }
}
