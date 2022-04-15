package com.feelydev.shroompoint.request;

import com.feelydev.shroompoint.utils.Credentials;
import com.feelydev.shroompoint.utils.CommunityDragonAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static CommunityDragonAPI dragonAPI = retrofit.create(CommunityDragonAPI.class);

    public static CommunityDragonAPI getGameDB(){
        return dragonAPI;
    }
}
