package com.kikebodi.tshirtapp.details;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kikebodi.tshirtapp.MainActivity;
import com.kikebodi.tshirtapp.R;
import com.kikebodi.tshirtapp.apiconnection.models.Shirt;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ShirtDetailFragment extends Fragment {

    private TextView title, colour, price, size;
    private ImageView image;
    private Shirt item;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shirt_detail, container, false);
        title =  view.findViewById(R.id.title);
        colour = view.findViewById(R.id.colour);
        price = view.findViewById(R.id.price);
        size = view.findViewById(R.id.size);
        image = view.findViewById(R.id.image);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageLoader.displayImage(item.getPicture(), image);
        title.setText(item.getName());
        colour.setText(item.getColour());
        price.setText(String.format("$%s", String.valueOf(item.getPrice())));
        size.setText(item.getSize().toUpperCase());

        //In case of back key pressed, add shopping cart icon
        ((MainActivity)getActivity()).setShoppingCartIconVisibility(true);
    }

    public void setShirt(Shirt item){
        this.item = item;
    }

}
