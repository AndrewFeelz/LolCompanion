package com.feelydev.shroompoint.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.feelydev.shroompoint.models.ChampionVerbose;
import com.feelydev.shroompoint.utils.APIExecutors;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class APIClient2 {

    //LiveData
    private MutableLiveData<ChampionVerbose> championVerbose;
    private static APIClient2 instance2;
    private RetrieveChampionRunnable2 retrieveChampionRunnable;

    public static APIClient2 getInstance() {
        if (instance2 == null){
            instance2 = new APIClient2();
        }
        return instance2;
    }

    private APIClient2(){
        championVerbose = new MutableLiveData<>();
    }

    public LiveData<ChampionVerbose> getChampion() {
        return championVerbose;
    }

    //To be called to get a single champion
    public void getChampionAPI(String champId){

        if (retrieveChampionRunnable != null){
            retrieveChampionRunnable = null;
        }

        retrieveChampionRunnable = new RetrieveChampionRunnable2(champId);

        final Future theHandler = APIExecutors.getInstance().getNetworkIO().submit(retrieveChampionRunnable);

        APIExecutors.getInstance().getNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancellation of Request
                theHandler.cancel(true);
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }

    //Retrieve data from RestAPI by runnable class
    private class RetrieveChampionRunnable2 implements Runnable{

        private String champId;
        boolean cancelRequest;

        public RetrieveChampionRunnable2(String champId) {
            this.champId = champId;
            cancelRequest = false;
        }

        @Override
        public void run() {

            try {
                Response<ChampionVerbose> response = getChampion(champId).execute();
                if (cancelRequest){
                    return;
                }
                if (response.code() == 200){
                    ChampionVerbose championVerbose = response.body();
                }else{
                    Log.v("Tag", "Error in Response: " + response.errorBody().toString());
                    championVerbose = null;
                }

            } catch (IOException e) {
                e.printStackTrace();
                championVerbose = null;
            }

        }

        private Call<ChampionVerbose> getChampion(String champId){
            return Service.getGameDB().getChampion(this.champId);
        }

        private void cancelRequest(){
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
}
