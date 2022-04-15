package com.feelydev.shroompoint.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.feelydev.shroompoint.models.ChampionSimple;
import com.feelydev.shroompoint.models.ChampionVerbose;
import com.feelydev.shroompoint.utils.APIExecutors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import okhttp3.Response;
import retrofit2.Call;

public class APIClient {

    //LiveData
    private MutableLiveData<List<ChampionSimple>> championList = new MutableLiveData<>();
    private MutableLiveData<ChampionVerbose> championVerbose = new MutableLiveData<>();

    private static APIClient instance;

    public static APIClient getInstance() {
        if (instance == null){
            instance = new APIClient();
        }
        return instance;
    }

    private APIClient(){
        championList = new MutableLiveData<>();
        championVerbose = new MutableLiveData<>();

    }

    public LiveData<List<ChampionSimple>> getChampionList() {
        return championList;
    }

    public LiveData<ChampionVerbose> getChampion() {
        return championVerbose;
    }

    public void queueCommunityDragonAPI(){

        final Future theHandler = APIExecutors.getInstance().getNetworkIO().submit();

        APIExecutors.getInstance().getNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancellation of Request
                theHandler.cancel(true);
            }
        }, 5000, TimeUnit.MILLISECONDS);
    }

    //Retrieve data from RestAPI by runnable class
    //2 types of query, technically 3
    private class RetrieveRunnable implements Runnable{

        private String champId;
        boolean cancelRequest;

        public RetrieveRunnable(String champId, boolean cancelRequest) {
            this.champId = champId;
            this.cancelRequest = cancelRequest;
        }

        @Override
        public void run() {

            try {
                Response response = getChampionList().execute();
                if (cancelRequest){
                    return;
                }
                if (response.code() == 200){
                    List<ChampionSimple> championSimpleList = new ArrayList<>()
                }

            } catch (IOException e) {
                e.printStackTrace();
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
