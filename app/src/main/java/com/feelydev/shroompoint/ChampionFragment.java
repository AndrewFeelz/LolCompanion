package com.feelydev.shroompoint;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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
        View view = inflater.inflate(R.layout.fragment_champion, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        championListViewModel = new ViewModelProvider(this).get(ChampionListViewModel.class);

        ObserveChangesToList();
        ConfigureRecyclerView();
        getChampionListAPI();

        return view;
    }

    //Call from VIEWMODELS
    private void getChampionListAPI() {
        championListViewModel.getChampionListAPI();
    }

    private void ObserveChangesToList(){
        championListViewModel.getChampionList().observe(getViewLifecycleOwner(), new Observer<List<ChampionSimple>>() {
            @Override
            public void onChanged(List<ChampionSimple> championSimples) {
                if (championSimples != null) {
                    championListRecyclerView.setChampionSimpleList(championSimples);
                }

            };
        });
    }

    //RecyclerView
    private void ConfigureRecyclerView() {
        championListRecyclerView = new ChampionListRecyclerView(this);
        recyclerView.setAdapter(championListRecyclerView);

        int numberOfColumns = ScreenUtility.calculateNumberOfColumns(getContext(), 140);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
    }

        @Override
    public void onChampionClick(int position) {
//            Toast.makeText(getContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            String champID = championListRecyclerView.getSelectedChampionId(position);

            Fragment fragment = new ChampionVerboseFragment();
            Bundle bundle = new Bundle();
            bundle.putString("champID", champID);
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.championFragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

    }
}