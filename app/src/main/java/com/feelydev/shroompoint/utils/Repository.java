package com.feelydev.shroompoint.utils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.feelydev.shroompoint.models.ChampionSimple;
import com.feelydev.shroompoint.models.ChampionVerbose;
import com.feelydev.shroompoint.request.APIClient;

import java.util.List;

public class Repository {

    private static Repository instance;

    private APIClient client;

    public static Repository getInstance(){
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }

    private Repository(){
        client = APIClient.getInstance();
    }

    public LiveData<List<ChampionSimple>> getChampionList(){return client.getChampionList();}

    public LiveData<ChampionVerbose> getChampion(){return client.getChampion();}

}
