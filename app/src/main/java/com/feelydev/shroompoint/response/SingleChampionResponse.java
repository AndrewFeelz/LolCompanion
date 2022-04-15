package com.feelydev.shroompoint.response;

import com.feelydev.shroompoint.models.ChampionVerbose;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleChampionResponse {

    @SerializedName("")
    @Expose
    private ChampionVerbose championVerbose;

    public ChampionVerbose getChampionVerbose(){
        return championVerbose;
    }

    @Override
    public String toString() {
        return "SingleChampionResponse{" +
                "championVerbose=" + championVerbose +
                '}';
    }
}
