package com.feelydev.shroompoint;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.feelydev.shroompoint.adapters.ChampionVerboseRecyclerView;
import com.feelydev.shroompoint.models.ChampionSimple;
import com.feelydev.shroompoint.models.ChampionVerbose;
import com.feelydev.shroompoint.viewmodels.ChapionViewModel;

public class ChampionVerboseFragment extends Fragment {

    //RecyclerView
    private ChampionVerboseRecyclerView championVerboseRecyclerView;

    //ViewModels
    private ChapionViewModel chapionViewModel;

    //Frag to Frag movement
    public ChampionVerboseFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.champion_verbose_fragment, container,false);

        String champID = savedInstanceState.getString("champID");

        chapionViewModel = new ViewModelProvider(this).get(ChapionViewModel.class);

        getChampionAPI(champID);
        return view;
    }

    private void getChampionAPI(String champID) { chapionViewModel.getChampionAPI(champID); }

    private void ObserveChangesToChampion(){
        chapionViewModel.getChampion().observe(getViewLifecycleOwner(), new Observer<ChampionVerbose>() {
            @Override
            public void onChanged(ChampionVerbose championVerbose) {
                if(championVerbose != null){
                    championVerboseRecyclerView.setChampionVerbose(championVerbose);
                }
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.champion_verbose_fragment);

    }
}
