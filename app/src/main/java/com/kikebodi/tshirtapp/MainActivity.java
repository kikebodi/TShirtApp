package com.kikebodi.tshirtapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kikebodi.tshirtapp.apiconnection.ApiConnectionManager;
import com.kikebodi.tshirtapp.main_list.ItemListFragment;
import com.kikebodi.tshirtapp.shopingcart.ShoppingCartFragment;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private Menu actionBarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        setContentView(R.layout.activity_main);
        /*Toolbar myToolbar = (Toolbar) findViewById(R.id.action_shopping_cart);
        setSupportActionBar(myToolbar);*/

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.placeholder, new ItemListFragment(), "Main fragment");
        transaction.commit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof ItemListFragment) {
            ApiConnectionManager apiManager = new ApiConnectionManager();
            apiManager.getTshirtsFromAPI(fragment);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        actionBarMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart_icon:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.placeholder, new ShoppingCartFragment(), "Shopping cart")
                        .addToBackStack(null)
                        .commit();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    // Handle back button at main fragment
    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count > 0) {
            getFragmentManager().popBackStack();
        }
    }

    public void setShoppingCartIconVisibility(boolean selection){
        if(actionBarMenu == null) return;
        actionBarMenu.findItem(R.id.cart_icon).setVisible(selection);
    }

}


