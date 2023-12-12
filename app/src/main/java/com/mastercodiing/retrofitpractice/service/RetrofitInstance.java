package com.mastercodiing.retrofitpractice.service;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {

    private static Retrofit retrofit=null;
    private static String BASE_URL="https://api.printful.com/";

    public static GetCountryDataService getService(){
        if(retrofit==null){
            retrofit=new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GetCountryDataService.class);
    }


}
