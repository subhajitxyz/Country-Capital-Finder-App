package com.mastercodiing.retrofitpractice.service;

import com.mastercodiing.retrofitpractice.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCountryDataService {
    @GET("countries")
    Call<Result> getResult();
}
