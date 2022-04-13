package com.feelydev.shroompoint;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.feelydev.shroompoint.Adapters.SimpleChampionAdapter;
import com.feelydev.shroompoint.Interfaces.CommunityDragonAPI;
import com.feelydev.shroompoint.Models.ChampionSimple;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    String errors;
    RecyclerView recyclerView;
    List<ChampionSimple> championSimples;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        championSimples = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CommunityDragonAPI theCall = retrofit.create(CommunityDragonAPI.class);
        Call<List<ChampionSimple>> allChampions = theCall.getAllChampions();
        allChampions.enqueue(new Callback<List<ChampionSimple>>() {
            @Override
            public void onResponse(Call<List<ChampionSimple>> call, Response<List<ChampionSimple>> response) {
                if (response.code() != 200){
                    errors = "Error with Connection";
                } else {
                    championSimples = response.body();
                    championSimples.remove(0);


                    PutDataIntoRecyclerView(championSimples);
                }
            }

            @Override
            public void onFailure(Call<List<ChampionSimple>> call, Throwable t) {
                errors = "No clue what happened";
            }
        });


        BottomNavigationItemView logoutBtn = findViewById(R.id.logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logout();
                }
            });

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }

    private void PutDataIntoRecyclerView(List<ChampionSimple> championSimples) {
        SimpleChampionAdapter adapter = new SimpleChampionAdapter(this, championSimples);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void setChampionListing(){
        PutDataIntoRecyclerView(championSimples);
    };


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

}