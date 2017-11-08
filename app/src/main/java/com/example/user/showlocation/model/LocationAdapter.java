package com.example.user.showlocation.model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.showlocation.MapsActivity;
import com.example.user.showlocation.R;

import java.util.List;



public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {

    private List<Locations> locationlist;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView latTextview,longTextView,timeTextview;

        public MyViewHolder(View itemView) {
            super(itemView);
            latTextview= (TextView) itemView.findViewById(R.id.tv_late);
            longTextView= (TextView) itemView.findViewById(R.id.tv_long);
            timeTextview= (TextView) itemView.findViewById(R.id.tv_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context=v.getContext();
                    Intent intent = new Intent(context, MapsActivity.class);
                    Bundle extras = new Bundle();
                    extras.putInt("position", getAdapterPosition());
                    intent.putExtras(extras);
                    context.startActivity(intent);
                }
            });
        }
    }

    public LocationAdapter(List<Locations> locationlist) {
        this.locationlist = locationlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View iView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_row, parent, false);
        return new MyViewHolder(iView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Locations locations=locationlist.get(position);
        holder.latTextview.setText(String.valueOf(locations.getLatitude()));
        holder.longTextView.setText(String.valueOf(locations.getLongitude()));
        holder.timeTextview.setText(locations.getUpdatedAt());
    }



    @Override
    public int getItemCount() {
        return locationlist.size();
    }
}
