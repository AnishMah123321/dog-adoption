package com.codefororlando.fyp.api;

import com.codefororlando.fyp.model.ServerResponse;
import com.codefororlando.fyp.model.UserPet;
import com.codefororlando.fyp.model.UserResponse;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServices {

    @Headers("Content-Type: application/json")
    @POST("register")
    Call<ServerResponse> userSignUp(@Body HashMap<String, Object> body);

    @Headers("Content-Type: application/json")
    @POST("login")
    Call<ServerResponse> userLogin(@Body HashMap<String, Object> body);

    @Headers("Content-Type: application/json")
    @POST("userPet")
    Call<UserResponse> userPet(@Body HashMap<String, Object> body);

    @Headers("Content-Type: application/json")
  @GET("/userPet/all")
   Call<List<UserPet>> getPet();

    @Headers("Content-Type: application/json")
    @POST("logout")
    Call<ServerResponse> logOutUser(@Body HashMap<String, Object> body);
}
