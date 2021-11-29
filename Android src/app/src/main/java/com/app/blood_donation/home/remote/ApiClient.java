package com.app.blood_donation.home.remote;



import com.app.blood_donation.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = Constant.MAIN_URL;
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient() {

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }

}