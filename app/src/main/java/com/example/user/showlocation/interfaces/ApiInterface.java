package com.example.user.showlocation.interfaces;


import com.example.user.showlocation.model.LatLongModel;
import com.example.user.showlocation.model.LatLongResponse;
import com.example.user.showlocation.model.Locations;
import com.example.user.showlocation.model.LoginResponse;
import com.example.user.showlocation.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/api/auth")
    Call<LoginResponse> getUserValidity(@Body User userLoginCredential);

    @POST("/api/setlocation")
    Call<LatLongResponse> savePost(@Header("Authorization") String authorization, @Body LatLongModel model);

    @GET("/api/getlocation")
    Call<List<Locations>> getHeroes(@Header("Authorization") String authorization);


}
