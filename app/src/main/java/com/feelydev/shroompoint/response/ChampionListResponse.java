package com.feelydev.shroompoint.response;

import com.feelydev.shroompoint.models.ChampionSimple;
import com.google.gson.annotations.Expose;

import java.util.List;

public class ChampionListResponse {

    @Expose
    private List<ChampionSimple> championList;

    public List<ChampionSimple> getChampionList(){
        championList.remove(0);
        return championList;
    }

    @Override
    public String toString() {
        return "ChampionListResponse{" +
                "championList=" + championList +
                '}';
    }
}
