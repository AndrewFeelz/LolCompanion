package com.feelydev.shroompoint;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.feelydev.shroompoint.models.ChampionSimple;
import com.feelydev.shroompoint.models.ChampionVerbose;
import com.feelydev.shroompoint.viewmodels.ChampionListViewModel;
import com.feelydev.shroompoint.viewmodels.ChapionViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class MainActivity extends FragmentActivity {

    RecyclerView recyclerView;
    List<ChampionSimple> championSimples;
    //ViewModel for ChampionList
    private ChampionListViewModel championListViewModel;
    private ChapionViewModel chapionViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        championListViewModel = new ViewModelProvider(this).get(ChampionListViewModel.class);
        chapionViewModel = new ViewModelProvider(this).get(ChapionViewModel.class);

        ObserveChanges();

        //Testing method of onclick
        getChampionListAPI();
        getChampionAPI("420");


        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);

        BottomNavigationItemView logoutBtn = findViewById(R.id.logout);
        logoutBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }


    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to log out?")
                .setTitle("Wait!")
                .setPositiveButton("For Sure", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        intent.putExtra(LoginActivity.EXTRA_CLEAR_CREDENTIALS, true);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Nah Dawg", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // CANCEL
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void ObserveChanges(){
        chapionViewModel.getChampion().observe(this, new Observer<ChampionVerbose>() {
            @Override
            public void onChanged(ChampionVerbose championVerbose) {
                if(championVerbose != null){
                    Log.v("Tag", "ITs yah boi: " + championVerbose.getName());
                }
            }
        });
    }

    //Observer changes in champion list data
//    private void ObserveChanges(){
//        championListViewModel.getChampionListAPI().observe(this, new Observer<List<ChampionSimple>>() {
//            @Override
//            public void onChanged(List<ChampionSimple> championSimples) {
//                if(championSimples != null){
//                    for (ChampionSimple championSimple: championSimples){
//                        Log.v("Tag", "On Changed: " + championSimple.getName());
//                    }
//                }
//
//            }
//        });
//    }

    //Call from VIEWMODEL
    private void getChampionListAPI(){
        championListViewModel.getChampionListAPI();
    }

    private void getChampionAPI(String champId){
        chapionViewModel.getChampionAPI(champId);
    }

}