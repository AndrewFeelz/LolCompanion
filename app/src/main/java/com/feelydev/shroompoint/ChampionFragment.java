package com.feelydev.shroompoint;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.feelydev.shroompoint.models.ChampionSimple;
import com.feelydev.shroompoint.viewmodels.ChampionListViewModel;

import java.util.List;

public class ChampionFragment extends Fragment {

    //ViewModel for ChampionList
    private ChampionListViewModel championListViewModel;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public ChampionFragment() {
        // comes here currently

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        championListViewModel = new ViewModelProvider(this).get(ChampionListViewModel.class);

        ObserveChanges();

        //Testing method of onclick
        getChampionListAPI();

    }

    //Observer changes in champion list data
    private void ObserveChanges(){
        championListViewModel.getChampionList().observe(this, new Observer<List<ChampionSimple>>() {
            @Override
            public void onChanged(List<ChampionSimple> championSimples) {
                if(championSimples != null){
                    for (ChampionSimple championSimple: championSimples){
                        Log.v("Tag", "On Changed: " + championSimple.getName());
                    }
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_champion, container, false);
    }

    //Call from VIEWMODEL
    private void getChampionListAPI(){
        championListViewModel.getChampionListAPI();
    }
}