package com.feelydev.shroompoint.request;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.feelydev.shroompoint.models.ChampionSimple;
import com.feelydev.shroompoint.models.ChampionVerbose;
import com.feelydev.shroompoint.utils.APIExecutors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class APIClient {

    //hi
    //LiveData
    private MutableLiveData<List<ChampionSimple>> championList;

    private static APIClient instance;

    //Global Request
    private RetrieveChampionListRunnable retrieveChampionListRunnable;



    public static APIClient getInstance() {
        if (instance == null){
            instance = new APIClient();
        }
        return instance;
    }

    private APIClient(){
        championList = new MutableLiveData<>();

    }

    public LiveData<List<ChampionSimple>> getChampionList() {
        return championList;
    }

    public void getChampionListAPI( ){

        if (retrieveChampionListRunnable != null){
            retrieveChampionListRunnable = null;
        }

        retrieveChampionListRunnable = new RetrieveChampionListRunnable();

        final Future theHandler = APIExecutors.getInstance().getNetworkIO().submit(retrieveChampionListRunnable);

        APIExecutors.getInstance().getNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancellation of Request
                theHandler.cancel(true);
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }

    private class RetrieveChampionListRunnable implements Runnable{

        boolean cancelRequest;

        public RetrieveChampionListRunnable() {
            cancelRequest = false;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void run() {

            try {
                Response<List<ChampionSimple>> response = getChampionList().execute();
                if (cancelRequest){
                    return;
                }
                if (response.code() == 200){
                    List<ChampionSimple> championSimpleList = response.body();
                    championSimpleList.remove(0);
                    championSimpleList.sort(Comparator.comparing(ChampionSimple::getName));
                    championList.postValue(championSimpleList);
                }else{
                    Log.v("Tag", "Error in Response: " + response.errorBody().string());
                    championList.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                championList.postValue(null);
            }

        }

        private Call<List<ChampionSimple>> getChampionList(){
            return Service.getGameDB().getAllChampions();
        }

        private void cancelRequest(){
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
}
