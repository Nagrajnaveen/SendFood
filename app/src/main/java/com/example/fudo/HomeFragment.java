package com.example.fudo;


import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fudo.databinding.FragmentHomeBinding;
import com.example.fudo.models.Example;
import com.example.fudo.models.Location;
import com.example.fudo.models.Location_;
import com.example.fudo.models.NearbyRestaurant;
import com.example.fudo.models.Restaurant;
import com.example.fudo.services.RestaurantService;
import com.example.fudo.services.ServiceBuilder;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment  //implements IDetailsAction
{
    private String TAG = "BaseActivity";

    private FragmentHomeBinding binder;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Restaurant> mRestaurantListItem;
//    IDetailsAction detailsAction;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (isOnline()){

            Toast.makeText(context, "connected to zomato api", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(context, "Please check internet connection", Toast.LENGTH_SHORT).show();

        }

    }

    protected boolean isOnline() {
        ConnectivityManager cm = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binder= DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);


//        Bundle bundle=getArguments().getBundle("restaurant_name");

        mRecyclerView=binder.recyclerView;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRestaurantListItem = new ArrayList<>();

        loadRecyclerViewData();

        return binder.getRoot();
    }

    private void loadRecyclerViewData() {


        RestaurantService restaurantService= ServiceBuilder.buildService(RestaurantService.class);
        final Call<Example> dataModelCall=restaurantService.getData();

        dataModelCall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                List<NearbyRestaurant> citiesArray=response.body().getNearbyRestaurants();
                Log.d(TAG, "onResponse: connected to zomato api.......");


                for (int i=0;i<citiesArray.size();i++) {
                    mRestaurantListItem.add(citiesArray.get(i).getRestaurant());

                }

                mAdapter=new RestaurantListAdapter(mRestaurantListItem,getActivity());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

//    public void setDetailsAction(IDetailsAction detailsAction) {
//        this.detailsAction = detailsAction;
//    }
//
//    @Override
//    public void onClick(Restaurant restaurant) {
//
//    }


}