package com.feelydev.shroompoint.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.feelydev.shroompoint.models.ChampionSimple;
import com.feelydev.shroompoint.utils.Repository;

import java.util.List;

public class ChampionListViewModel extends ViewModel {

    //Get From Repository
    private Repository repository;

    //Constructor
    public ChampionListViewModel() {
        repository = Repository.getInstance();
    }

    public LiveData<List<ChampionSimple>> getChampionList(){
        return repository.getChampionList();
    }

    //Calling method in Repository
    public void getChampionListAPI(){
        repository.getChampionListAPI();
    }
}
