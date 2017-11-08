package com.example.user.showlocation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.user.showlocation.R;
import com.example.user.showlocation.interfaces.ApiInterface;
import com.example.user.showlocation.model.DatabaseHandler;
import com.example.user.showlocation.model.LocationAdapter;
import com.example.user.showlocation.model.Locations;
import com.example.user.showlocation.retrofit.RetrofitApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetLocation extends AppCompatActivity {
    private ApiInterface apiInterface;
    private String tokenb;
    RecyclerView recyclerView;
    List<Locations> listing;
    private LinearLayoutManager layoutManager;
    LocationAdapter locationAdapter;

    //for sqlite database ..

    DatabaseHandler dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(GetLocation.this);
        recyclerView.setLayoutManager(layoutManager);

        listing = new ArrayList<>();
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        if (getIntent().hasExtra("token")) {
            tokenb = getIntent().getStringExtra("token");
        }

        getLocation();
    }



    public void getLocation(){
        apiInterface.getHeroes("Bearer " + tokenb).enqueue(new Callback<List<Locations>>() {
            @Override
            public void onResponse(Call<List<Locations>> call, Response<List<Locations>> response) {
                List<Locations> locationlist = response.body();
                Locations locations=null;
                for (int i=0;i<locationlist.size();i++){
                    locations=new Locations();
                    Double lat_value = locationlist.get(i).getLatitude();
                    Double log_value=locationlist.get(i).getLongitude();
                    String update_value=locationlist.get(i).getUpdatedAt();
                    locations.setLatitude(lat_value);
                    locations.setLongitude(log_value);
                    locations.setUpdatedAt(update_value);
                    locationAdapter=new LocationAdapter(locationlist);
                    recyclerView.setAdapter(locationAdapter);

                    dbHelper = new DatabaseHandler(GetLocation.this);
                    dbHelper.insertInformation(lat_value,log_value);


                }



            }

            @Override
            public void onFailure(Call<List<Locations>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
