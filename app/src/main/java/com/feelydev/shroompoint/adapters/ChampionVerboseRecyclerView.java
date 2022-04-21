package com.feelydev.shroompoint.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.feelydev.shroompoint.R;
import com.feelydev.shroompoint.models.ChampionSimple;
import com.feelydev.shroompoint.models.ChampionVerbose;

import java.util.List;

public class ChampionVerboseRecyclerView  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ChampionVerbose championVerbose;

    public ChampionVerboseRecyclerView(ChampionVerbose championVerbose) {
        this.championVerbose = championVerbose;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.champion_verbose_fragment, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setChampion(ChampionVerbose champV){
        this.championVerbose = champV;
    }

    public ChampionVerbose getChampion(){
        return championVerbose;
    }

}
