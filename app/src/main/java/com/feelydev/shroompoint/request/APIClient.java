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

    //LiveData
    private MutableLiveData<ChampionVerbose> championVerbose;
    private MutableLiveData<List<ChampionSimple>> championList;

    private static APIClient instance;

    //Global Request
    private RetrieveChampionRunnable retrieveChampionRunnable;
    private RetrieveChampionListRunnable retrieveChampionListRunnable;



    public static APIClient getInstance() {
        if (instance == null){
            instance = new APIClient();
        }
        return instance;
    }

    private APIClient(){
        championVerbose = new MutableLiveData<>();
        championList = new MutableLiveData<>();

    }

    public LiveData<ChampionVerbose> getChampion() {
        return championVerbose;
    }
    public LiveData<List<ChampionSimple>> getChampionList() {
        return championList;
    }



    //To be called to get a single champion
    public void getChampionAPI(String champId){

        if (retrieveChampionRunnable != null){
            retrieveChampionRunnable = null;
        }

        retrieveChampionRunnable = new RetrieveChampionRunnable(champId);

        final Future theHandler = APIExecutors.getInstance().getNetworkIO().submit(retrieveChampionRunnable);

        APIExecutors.getInstance().getNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Cancellation of Request
                theHandler.cancel(true);
            }
        }, 5000, TimeUnit.MILLISECONDS);
    }

    //Retrieve data from RestAPI by runnable class
    private class RetrieveChampionRunnable implements Runnable{

        private String champId;
        boolean cancelRequest;

        public RetrieveChampionRunnable(String champId) {
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
                    ChampionVerbose champion = response.body();
                    championVerbose.postValue(champion);
                }else{
                    Log.v("Tag", "Error in Response: " + response.errorBody().toString());
                    championVerbose.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                championVerbose.postValue(null);
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
        }, 5000, TimeUnit.MILLISECONDS);
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
