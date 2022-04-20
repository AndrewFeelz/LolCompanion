package com.feelydev.shroompoint.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.feelydev.shroompoint.R;
import com.feelydev.shroompoint.models.ChampionVerbose;

public class ChampionVerboseRecyclerView  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ChampionVerbose championVerbose;

    public ChampionVerboseRecyclerView() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.champion_verbose_fragment, parent, false);
        return new ChampionVerboseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ChampionVerboseViewHolder)holder).txtName.setText(championVerbose.getName());
    }

    @Override
    public int getItemCount() {
        if (championVerbose == null){
            return 0;
        }
        return 1;
    }

    public void setChampionVerbose(ChampionVerbose championVerbose) {
        this.championVerbose = championVerbose;
        notifyDataSetChanged();
    }
}
