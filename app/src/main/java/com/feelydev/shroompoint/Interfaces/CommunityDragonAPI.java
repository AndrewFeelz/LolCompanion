package com.feelydev.shroompoint.Interfaces;

import com.feelydev.shroompoint.Models.ChampionSimple;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CommunityDragonAPI {

    @GET("champion-summary.json")
    Call<List<ChampionSimple>> getAllChampions();
}
