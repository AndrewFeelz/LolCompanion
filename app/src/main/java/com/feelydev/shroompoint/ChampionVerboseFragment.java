package com.feelydev.shroompoint;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.feelydev.shroompoint.adapters.ChampionListRecyclerView;
import com.feelydev.shroompoint.adapters.ChampionVerboseRecyclerView;
import com.feelydev.shroompoint.adapters.ChampionVerboseViewHolder;
import com.feelydev.shroompoint.models.ChampionSimple;
import com.feelydev.shroompoint.models.ChampionVerbose;
import com.feelydev.shroompoint.models.Spell;
import com.feelydev.shroompoint.request.APIClient;
import com.feelydev.shroompoint.request.Service;
import com.feelydev.shroompoint.utils.Credentials;
import com.feelydev.shroompoint.viewmodels.ChapionViewModel;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChampionVerboseFragment extends FragmentActivity {

    //ViewModels
    private ChapionViewModel chapionViewModel;
    private String champID;
    private ChampionVerbose championVerbose;

            //Widgets
    ImageView imgSplash, imgPassive, imgQ, imgW, imgE, imgR;
    TextView txtName, txtTitle, txtBio, txtDifficulty, txtDamage, txtDurability, txtCC, txtMobility, txtUtility, txtPassiveName, txtPassiveDesc, txtQName, txtQDesc, txtWName, txtWDesc, txtEName, txtEDesc, txtRName, txtRDesc;


    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        chapionViewModel = new ViewModelProvider(this).get(ChapionViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.champion_verbose_fragment);
        Bundle bundle = MainActivity.getSavedData();
        champID = bundle.getString("champID");
        getChampionAPI(champID);

        imgSplash = findViewById(R.id.imgChampionSplash);
        imgPassive = findViewById(R.id.imgPassive);
        imgQ = findViewById(R.id.imgQ);
        imgW = findViewById(R.id.imgW);
        imgE = findViewById(R.id.imgE);
        imgR = findViewById(R.id.imgR);

        txtName = findViewById(R.id.txtChampName);
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


        SetChampion();
        hideSystemBars();
    }

    private void doItYourself(String champID) {
        Service.getGameDB().getChampion(champID).enqueue(new Callback<ChampionVerbose>() {
            @Override
            public void onResponse(Call<ChampionVerbose> call, Response<ChampionVerbose> response) {
                if (response.code() != 200){
                    Log.v("Taggy", "I am a loser ");
                } else{
                    championVerbose = response.body();
                }
            }

            @Override
            public void onFailure(Call<ChampionVerbose> call, Throwable t) {

            }
        });

    }

    private void SetChampion() {
        doItYourself(champID);

        ArrayList<ImageView> spellImgList = new ArrayList<>();
        ArrayList<TextView> spellNameList = new ArrayList<>();
        ArrayList<TextView> spellDescList = new ArrayList<>();
        //counter variable
        int i = 0;
        Collections.addAll(spellImgList, imgQ, imgW, imgE, imgR);
        Collections.addAll(spellNameList, txtQName, txtWName, txtEName, txtRName);
        Collections.addAll(spellDescList, txtQDesc, txtWDesc, txtEDesc, txtRDesc );

        Glide.with(this)
                .load(Credentials.CHAMP_SPLASH_URL + championVerbose.getId() + "/" + championVerbose.getId() + "000.jpg")
                .into(imgSplash);
        txtName.setText(championVerbose.getName());
        txtTitle.setText(championVerbose.getTitle());
        txtBio.setText(championVerbose.getShortBio());
        Log.v("Taggy", "My Champ is "+ championVerbose.getName());
        txtDifficulty.setText(championVerbose.getTacticalInfo().getDifficulty());
        txtDamage.setText(championVerbose.getPlaystyleInfo().getDamage());
        txtDurability.setText(championVerbose.getPlaystyleInfo().getDurability());
        txtCC.setText(championVerbose.getPlaystyleInfo().getCrowdControl());
        txtMobility.setText(championVerbose.getPlaystyleInfo().getMobility());
        txtUtility.setText(championVerbose.getPlaystyleInfo().getUtility());

        Glide.with(this)
                .load(Credentials.CHAMP_SPELL_SQUARE_URL + championVerbose.getPassive().getThumbnailPath())
                .into(imgPassive);
        txtPassiveName.setText(championVerbose.getPassive().getName());
        txtPassiveDesc.setText(championVerbose.getPassive().getDesc());

        for (Spell spell: championVerbose.getSpells()) {
            ImageView img =  spellImgList.get(i);
            Glide.with(this)
                    .load(Credentials.CHAMP_SPELL_SQUARE_URL + spell.getThumbnailPath())
                    .into(img);

            TextView name = spellNameList.get(i);
            name.setText(spell.getName());

            TextView desc = spellDescList.get(i);
            desc.setText(spell.getDesc());

            i++;
        }
    }


    private void getChampionAPI(String champID) { chapionViewModel.getChampionAPI(champID); }

    private void hideSystemBars() {
        WindowInsetsControllerCompat windowInsetsController =
                ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController == null) {
            return;
        }
        // Configure the behavior of the hidden system bars
        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
    }

}
