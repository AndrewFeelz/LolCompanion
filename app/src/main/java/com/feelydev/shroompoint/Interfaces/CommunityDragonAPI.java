package com.feelydev.shroompoint.Interfaces;

import com.feelydev.shroompoint.Models.ChampionSimple;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CommunityDragonAPI {

    @GET("champion-summary.json")
    Call<ChampionSimple> getAllChampions();
}
