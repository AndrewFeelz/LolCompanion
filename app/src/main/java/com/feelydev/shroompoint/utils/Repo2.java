package com.feelydev.shroompoint.utils;

import androidx.lifecycle.LiveData;

import com.feelydev.shroompoint.models.ChampionSimple;
import com.feelydev.shroompoint.models.ChampionVerbose;
import com.feelydev.shroompoint.request.APIClient;
import com.feelydev.shroompoint.request.APIClient2;

import java.util.List;

public class Repo2 {
    private static Repo2 instance;

    private APIClient2 client2;

    public static Repo2 getInstance(){
        if(instance == null){
            instance = new Repo2();
        }
        return instance;
    }

    private Repo2(){
        client2 = APIClient2.getInstance();
    }


    //Champ Verbose Data from APICLIENT
    public LiveData<ChampionVerbose> getChampion(){return client2.getChampion();}
    public void getChampionAPI(String champId){
        client2.getChampionAPI(champId);
    }
}
