package com.example.user.showlocation;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.user.showlocation.interfaces.ApiInterface;
import com.example.user.showlocation.model.DatabaseHandler;
import com.example.user.showlocation.model.Locations;
import com.example.user.showlocation.retrofit.RetrofitApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    DatabaseHandler handler;
    Geocoder geocoder;
    List<Address> addresses;

    private ApiInterface apiInterface;
    private GoogleMap mMap;
    private List<Locations> locationlist;
    int position;
    Double latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        position=bundle.getInt("position");

        handler=new DatabaseHandler(this);
        locationlist=new ArrayList<Locations>();
        locationlist=handler.getInformations();
        if (locationlist.size()>0){
            latitude = locationlist.get(locationlist.size() - 1).getLatitude();
            longitude = locationlist.get(locationlist.size() - 1).getLongitude();

            updateMap();
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        updateMap();
    }

    private void updateMap() {
        if (mMap == null || latitude == null) return;


        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            LatLng sydney = new LatLng(latitude, longitude);
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();

            String location=""+address+" ,"+city+" ,"+state+" ,"+country+" ,"+postalCode+"";
            mMap.addMarker(new MarkerOptions().position(sydney).title(location));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
