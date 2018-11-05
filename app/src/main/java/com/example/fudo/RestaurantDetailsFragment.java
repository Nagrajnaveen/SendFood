package com.example.fudo;

import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fudo.databinding.RestaurantDetailsFragmentBinding;
import com.squareup.picasso.Picasso;

public class RestaurantDetailsFragment extends Fragment //implements
{

    RestaurantDetailsFragmentBinding binder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binder= DataBindingUtil.inflate(inflater,R.layout.restaurant_details_fragment,container,false);

        Intent intent=getActivity().getIntent();

        binder.restaurantName.setText(intent.getStringExtra("name"));
        binder.cuisinesDetails.setText(intent.getStringExtra("cuisines"));
        binder.userRating.setText(intent.getStringExtra("user_rating"));
        Picasso.get().load(intent.getStringExtra("feature_img")).into(binder.restImage);




        return binder.getRoot();
    }
}
