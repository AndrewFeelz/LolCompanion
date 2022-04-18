package com.feelydev.shroompoint.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.feelydev.shroompoint.ChampionFragment;
import com.feelydev.shroompoint.R;
import com.feelydev.shroompoint.models.ChampionSimple;

import java.util.List;

public class ChampionListRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChampionSimple> championSimpleList;
    private OnChampionListener onChampionListener;

    public ChampionListRecyclerView(OnChampionListener onChampionListener) {
        this.onChampionListener = onChampionListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.champion_list_item, parent, false);
        return new ChampionListViewHolder(view, onChampionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        ((ChampionListViewHolder)holder).name.setText(championSimpleList.get(i).getName());

        //Use Glide for image
        Glide.with(holder.itemView.getContext())
                .load(championSimpleList.get(i))
                .into((((ChampionListViewHolder)holder).championThumbnail));
        
    }

    @Override
    public int getItemCount() {
        return championSimpleList.size();
    }

    public void setChampionSimpleList(List<ChampionSimple> championSimples){
        this.championSimpleList = championSimples;
        notifyDataSetChanged();
    }
}
