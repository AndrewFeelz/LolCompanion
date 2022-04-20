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


    //6 imageViews
    private ImageView imgSplash, imgPassive, imgQ, imgW, imgE, imgR;
    //19 textViews
    private TextView txtName, txtTitle, txtBio, txtDifficulty, txtDamage, txtDurability, txtCC, txtMobility, txtUtility, txtPassiveName, txtPassiveDesc, txtQName, txtQDesc, txtWName, txtWDesc, txtEName, txtEDesc, txtRName, txtRDesc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.champion_verbose_fragment, container,false);

        String champID = savedInstanceState.getString("champID");

        chapionViewModel = new ViewModelProvider(this).get(ChapionViewModel.class);


        return view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.champion_verbose_fragment);

        imgSplash = findViewById(R.id.imgChampionSplash);
        imgPassive = findViewById(R.id.imgPassive);
        imgQ = findViewById(R.id.imgQ);
        imgW = findViewById(R.id.imgW);
        imgE = findViewById(R.id.imgE);
        imgR = findViewById(R.id.imgR);

        txtName = findViewById(R.id.txtChampionName);
        txtTitle = findViewById(R.id.txtChampTitle);
        txtBio = findViewById(R.id.txtBio);
        txtDifficulty = findViewById(R.id.txtDifficultyNum);
        txtDamage = findViewById(R.id.txtDamageNum);
        txtDurability = findViewById(R.id.txtDurabilityNum);
        txtCC = findViewById(R.id.txtCCNum);
        txtMobility = findViewById(R.id.txtMobilityNum);
        txtUtility = findViewById(R.id.txtUtilityNum);
        txtPassiveName = findViewById(R.id.txtPassiveName);
        txtPassiveDesc = findViewById(R.id.txtPassiveDescription);
        txtQName = findViewById(R.id.txtQName);
        txtQDesc = findViewById(R.id.txtQDescription);
        txtWName = findViewById(R.id.txtWName);
        txtWDesc = findViewById(R.id.txtWDescription);
        txtEName = findViewById(R.id.txtEName);
        txtEDesc = findViewById(R.id.txtEDescription);
        txtRName = findViewById(R.id.txtRName);
        txtRDesc = findViewById(R.id.txtRDescription);



    }
}
