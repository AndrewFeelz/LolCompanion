package com.feelydev.shroompoint.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.feelydev.shroompoint.R;
import com.feelydev.shroompoint.models.ChampionVerbose;
import com.feelydev.shroompoint.models.Spell;
import com.feelydev.shroompoint.utils.Credentials;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        ArrayList<ImageView> spellImgList = new ArrayList<>();
        ArrayList<TextView> spellNameList = new ArrayList<>();
        ArrayList<TextView> spellDescList = new ArrayList<>();
        //counter variable
        int i = 0;
        Collections.addAll(spellImgList, ((ChampionVerboseViewHolder)holder).imgQ, ((ChampionVerboseViewHolder)holder).imgW, ((ChampionVerboseViewHolder)holder).imgE, ((ChampionVerboseViewHolder)holder).imgR);
        Collections.addAll(spellNameList, ((ChampionVerboseViewHolder)holder).txtQName, ((ChampionVerboseViewHolder)holder).txtWName, ((ChampionVerboseViewHolder)holder).txtEName, ((ChampionVerboseViewHolder)holder).txtRName);
        Collections.addAll(spellDescList, ((ChampionVerboseViewHolder)holder).txtQDesc, ((ChampionVerboseViewHolder)holder).txtWDesc, ((ChampionVerboseViewHolder)holder).txtEDesc, ((ChampionVerboseViewHolder)holder).txtRDesc );

        Glide.with(holder.itemView.getContext())
                .load(Credentials.CHAMP_SPLASH_URL + championVerbose.getId() + "/" + championVerbose.getId() + "000.jpg")
                .into(((ChampionVerboseViewHolder)holder).imgSplash);
        ((ChampionVerboseViewHolder)holder).txtName.setText(championVerbose.getName());
        ((ChampionVerboseViewHolder)holder).txtTitle.setText(championVerbose.getTitle());
        ((ChampionVerboseViewHolder)holder).txtBio.setText(championVerbose.getShortBio());
        ((ChampionVerboseViewHolder)holder).txtDifficulty.setText(championVerbose.getTacticalInfo().getDifficulty());
        ((ChampionVerboseViewHolder)holder).txtDamage.setText(championVerbose.getPlaystyleInfo().getDamage());
        ((ChampionVerboseViewHolder)holder).txtDurability.setText(championVerbose.getPlaystyleInfo().getDurability());
        ((ChampionVerboseViewHolder)holder).txtCC.setText(championVerbose.getPlaystyleInfo().getCrowdControl());
        ((ChampionVerboseViewHolder)holder).txtMobility.setText(championVerbose.getPlaystyleInfo().getMobility());
        ((ChampionVerboseViewHolder)holder).txtUtility.setText(championVerbose.getPlaystyleInfo().getUtility());

        Glide.with(holder.itemView.getContext())
                .load(Credentials.CHAMP_SPELL_SQUARE_URL + championVerbose.getPassive().getThumbnailPath())
                .into(((ChampionVerboseViewHolder)holder).imgPassive);
        ((ChampionVerboseViewHolder)holder).txtPassiveName.setText(championVerbose.getPassive().getName());
        ((ChampionVerboseViewHolder)holder).txtPassiveDesc.setText(championVerbose.getPassive().getDesc());

        for (Spell spell: championVerbose.getSpells()) {
            ImageView img =  spellImgList.get(i);
            Glide.with(holder.itemView.getContext())
                    .load(Credentials.CHAMP_SPELL_SQUARE_URL + spell.getThumbnailPath())
                    .into(img);

            TextView name = spellNameList.get(i);
            name.setText(spell.getName());

            TextView desc = spellDescList.get(i);
            desc.setText(spell.getDesc());

            i++;
        }
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
