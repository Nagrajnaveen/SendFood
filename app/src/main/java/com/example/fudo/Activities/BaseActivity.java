package com.example.fudo.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fudo.HomeFragment;
import com.example.fudo.R;


public class BaseActivity extends AppCompatActivity {

    private String TAG="BaseActivity";


    private static final String FTAG_HOME_FRAGMENT = "fragmentSearch";
    private HomeFragment homeFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

//        if (homeFragment==null) {

            homeFragment = new HomeFragment();

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.restaurent_list_container, homeFragment, FTAG_HOME_FRAGMENT);
            ft.commit();

//        }
    }





    //

}

