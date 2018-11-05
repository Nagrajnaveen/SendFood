package com.example.fudo.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.fudo.Activities.BaseActivity;
import com.example.fudo.R;
import com.example.fudo.RestaurantDetailsFragment;

public class RestaurantDetails extends AppCompatActivity {

    private static final String FTAG_RESTAURANT_DETAILS_FRAGMENT = "DetailsFragment";
    private RestaurantDetailsFragment restaurantDetailsFragment;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    Intent launchIntent=new Intent(getApplicationContext(),BaseActivity.class);
                    startActivity(launchIntent);

                    return true;
                case R.id.web_url:
//                    mTextMessage.setText(R.string.title_dashboard);
                Intent browserIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(getIntent().getStringExtra("web_url")));
                startActivity(browserIntent);
                    return true;
                case R.id.photos_url:
//                    mTextMessage.setText(R.string.title_notifications);
                 Intent photoBrowserIntent=new Intent(Intent.ACTION_VIEW,Uri.parse(getIntent().getStringExtra("photos_url")));
                 startActivity(photoBrowserIntent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botom_navigation);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        restaurantDetailsFragment=new RestaurantDetailsFragment();
//        restaurantDetailsFragment.setArguments(bundle);
        FragmentManager fm= getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(R.id.restaurant_details_container,restaurantDetailsFragment,FTAG_RESTAURANT_DETAILS_FRAGMENT);
        ft.commit();
    }

}
