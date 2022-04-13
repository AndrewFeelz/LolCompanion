package com.feelydev.shroompoint.Adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.feelydev.shroompoint.Models.ChampionSimple;
import com.feelydev.shroompoint.R;

import java.util.List;
import java.util.zip.Inflater;

public class SimpleChampionAdapter extends RecyclerView.Adapter<SimpleChampionAdapter.MyViewHolder> {

    private Context mContext;
    private List<ChampionSimple> simpleChampionList;

    public SimpleChampionAdapter(Context mContext, List<ChampionSimple> simpleChampionList) {
        this.mContext = mContext;
        this.simpleChampionList = simpleChampionList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.champion_list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String imageUrl = "https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-icons/" + simpleChampionList.get(position).getId() + ".png";
        holder.champName.setText(simpleChampionList.get(position).getName());
        Glide.with(mContext)
                .load(imageUrl)
                .into(holder.champThumbnail);
    }

    @Override
    public int getItemCount() {
        return simpleChampionList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView champName;
        ImageView champThumbnail;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            champName = itemView.findViewById(R.id.textChampSimple);
            champThumbnail = itemView.findViewById(R.id.imageChampSimple);


        }
    }
}
