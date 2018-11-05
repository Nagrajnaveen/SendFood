package com.example.fudo;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.fudo.Activities.RestaurantDetails;
import com.example.fudo.databinding.ResturantListItemBinding;
import com.example.fudo.models.Example;
import com.example.fudo.models.Location;
import com.example.fudo.models.Location_;
import com.example.fudo.models.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;

class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>{


    private List<Restaurant> mResListItems;


    private Context context;
//    private IDetailsAction detailsAction;

//    public void setRestaurants(Example restaurants) {
//        this.restaurants = restaurants;
//    }

    public RestaurantListAdapter(List<Restaurant> mResListItems, Context context) {
        this.mResListItems = mResListItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       return new ViewHolder(
               (ResturantListItemBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                       R.layout.resturant_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.onBind(position);
    }

    @Override
    public int getItemCount() {
       return mResListItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private Example restaurants;
        private Location_ detailLocation;
        private Location location;

        private ResturantListItemBinding binder;

        public ViewHolder(ResturantListItemBinding binder) {
            super(binder.getRoot());



            binder.restCardView.setOnClickListener(clickListener);
            this.binder=binder;

        }


        public void onBind(final int position) {

            binder.name.setText(mResListItems.get(position).getName());
            binder.cuisens.setText(mResListItems.get(position).getCuisines());

            if (mResListItems.get(position).getFeaturedImage().isEmpty()){
                Picasso.get().load(R.drawable.ic_launcher_foreground).into(binder.resturantIv);
            }
            else{
                Picasso.get().load(mResListItems.get(position).getFeaturedImage()).into(binder.resturantIv);
            }


                }

                private View.OnClickListener clickListener= new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent=new Intent(v.getContext(),RestaurantDetails.class);
                        intent.putExtra("name",mResListItems.get(getAdapterPosition()).getName());
                        intent.putExtra("feature_img",mResListItems.get(getAdapterPosition()).getFeaturedImage());
                        intent.putExtra("user_rating",mResListItems.get(getAdapterPosition()).getUserRating().getAggregateRating());
                        intent.putExtra("menu_url",mResListItems.get(getAdapterPosition()).getMenuUrl());
                        intent.putExtra("photos_url",mResListItems.get(getAdapterPosition()).getPhotosUrl());
                        intent.putExtra("web_url",mResListItems.get(getAdapterPosition()).getUrl());
                        intent.putExtra("cuisines",mResListItems.get(getAdapterPosition()).getCuisines());
                        v.getContext().startActivity(intent);

                    }
                };
            }
        }









