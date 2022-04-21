package com.feelydev.shroompoint.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.feelydev.shroompoint.models.ChampionSimple;
import com.feelydev.shroompoint.models.ChampionVerbose;
import com.feelydev.shroompoint.utils.Repo2;
import com.feelydev.shroompoint.utils.Repository;

import java.util.List;

public class ChapionViewModel extends ViewModel {

    //repository Get
    private Repo2 repository;

    public ChapionViewModel() { repository = Repo2.getInstance(); }

    public LiveData<ChampionVerbose> getChampion(){
        return repository.getChampion();
    }

    public void getChampionAPI(String champId){
        repository.getChampionAPI(champId);
    }
}
