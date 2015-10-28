package com.example;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface RandomNumberService {
    @GET("jsonI.php?type=uint8")
    Call<RandomNumberResponse> getRandomNumbers(@Query("length") int length);

    @GET("jsonI.php?type=uint8")
    Call<InvalidRandomNumberResponse> getInvalidRandomNumbers(@Query("length") int length);

    @GET("foo.php")
    Call<RandomNumberResponse> getFoo();
}
