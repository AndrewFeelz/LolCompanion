package com.feelydev.shroompoint.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.feelydev.shroompoint.R;

public class ChampionListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //Widgets
    TextView name;
    ImageView championThumbnail;

    //ClickListener
    OnChampionListener ChampionListener;

    public ChampionListViewHolder(@NonNull View itemView, OnChampionListener onChampionListener) {
        super(itemView);
        ChampionListener = onChampionListener;

        name = itemView.findViewById(R.id.txtChampionName);
        championThumbnail = itemView.findViewById(R.id.imgChampionThumbnail);
        //may
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ChampionListener.onChampionClick(getAdapterPosition());
    }
}
