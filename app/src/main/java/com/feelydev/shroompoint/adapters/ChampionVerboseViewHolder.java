package com.feelydev.shroompoint.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.feelydev.shroompoint.R;

public class ChampionVerboseViewHolder extends RecyclerView.ViewHolder {

    //Widgets
    ImageView imgSplash, imgPassive, imgQ, imgW, imgE, imgR;
    TextView txtName, txtTitle, txtBio, txtDifficulty, txtDamage, txtDurability, txtCC, txtMobility, txtUtility, txtPassiveName, txtPassiveDesc, txtQName, txtQDesc, txtWName, txtWDesc, txtEName, txtEDesc, txtRName, txtRDesc;

    public ChampionVerboseViewHolder(@NonNull View itemView) {
        super(itemView);

        imgSplash = itemView.findViewById(R.id.imgChampionSplash);
        imgPassive = itemView.findViewById(R.id.imgPassive);
        imgQ = itemView.findViewById(R.id.imgQ);
        imgW = itemView.findViewById(R.id.imgW);
        imgE = itemView.findViewById(R.id.imgE);
        imgR = itemView.findViewById(R.id.imgR);

        txtName = itemView.findViewById(R.id.txtChampionName);
        txtTitle = itemView.findViewById(R.id.txtChampTitle);
        txtBio = itemView.findViewById(R.id.txtBio);
        txtDifficulty = itemView.findViewById(R.id.txtDifficultyNum);
        txtDamage = itemView.findViewById(R.id.txtDamageNum);
        txtDurability = itemView.findViewById(R.id.txtDurabilityNum);
        txtCC = itemView.findViewById(R.id.txtCCNum);
        txtMobility = itemView.findViewById(R.id.txtMobilityNum);
        txtUtility = itemView.findViewById(R.id.txtUtilityNum);
        txtPassiveName = itemView.findViewById(R.id.txtPassiveName);
        txtPassiveDesc = itemView.findViewById(R.id.txtPassiveDescription);
        txtQName = itemView.findViewById(R.id.txtQName);
        txtQDesc = itemView.findViewById(R.id.txtQDescription);
        txtWName = itemView.findViewById(R.id.txtWName);
        txtWDesc = itemView.findViewById(R.id.txtWDescription);
        txtEName = itemView.findViewById(R.id.txtEName);
        txtEDesc = itemView.findViewById(R.id.txtEDescription);
        txtRName = itemView.findViewById(R.id.txtRName);
        txtRDesc = itemView.findViewById(R.id.txtRDescription);

    }
}
