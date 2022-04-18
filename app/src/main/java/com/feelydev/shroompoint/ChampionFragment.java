package com.feelydev.shroompoint;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.feelydev.shroompoint.adapters.ChampionListRecyclerView;
import com.feelydev.shroompoint.adapters.OnChampionListener;
import com.feelydev.shroompoint.models.ChampionSimple;
import com.feelydev.shroompoint.utils.ScreenUtility;
import com.feelydev.shroompoint.viewmodels.ChampionListViewModel;
import com.feelydev.shroompoint.viewmodels.ChapionViewModel;

import java.util.List;

public class ChampionFragment extends Fragment implements OnChampionListener {

    //RecyclerView
    private RecyclerView recyclerView;
    private ChampionListRecyclerView championListRecyclerView;

    //ViewModels
    private ChampionListViewModel championListViewModel;
    private ChapionViewModel chapionViewModel;

    //Empty Constructor
    public ChampionFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView.findViewById(R.id.recyclerView);

        championListViewModel = new ViewModelProvider(this).get(ChampionListViewModel.class);

        ObserveChangesToList();
        getChampionListAPI();
        ConfigureRecyclerView();
        return inflater.inflate(R.layout.fragment_champion, container, false);
    }

    //Call from VIEWMODELS
    private List<ChampionSimple> getChampionListAPI() {
        championListViewModel.getChampionListAPI();
        List<ChampionSimple> championSimpleList = (List<ChampionSimple>) championListViewModel.getChampionList();
        if (championSimpleList != null) {
            for (ChampionSimple championSimple : championSimpleList) {
                Log.v("Tag", "On Changed: " + championSimple.getName());
            }
            return championSimpleList;
        }
        return null;
    }

    private void ObserveChangesToList(){
        championListViewModel.getChampionList().observe(getViewLifecycleOwner(), new Observer<List<ChampionSimple>>() {
            @Override
            public void onChanged(List<ChampionSimple> championSimples) {
                if (championSimples != null) {
                    for (ChampionSimple championSimple : championSimples) {
                        Log.v("Tag", "On Changed: " + championSimple.getName());
                    }
                }

            };
        });
    }

    //RecyclerView
    private void ConfigureRecyclerView() {
        int numberOfColumns = ScreenUtility.calculateNumberOfColumns(getContext(), 140);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        championListRecyclerView.setChampionSimpleList(getChampionListAPI());
        recyclerView.setAdapter(championListRecyclerView);
    }

        @Override
    public void onChampionClick(int position) {

    }
}