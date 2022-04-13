package com.feelydev.shroompoint;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feelydev.shroompoint.Adapters.SimpleChampionAdapter;
import com.feelydev.shroompoint.Interfaces.CommunityDragonAPI;
import com.feelydev.shroompoint.Models.ChampionSimple;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChampionFragment extends Fragment {

    private Context context;
    RecyclerView recyclerView;
    List<ChampionSimple> championSimples;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public ChampionFragment() {
        // comes here currently
        Log.v(String.valueOf(1),"Fuck This");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(String.valueOf(1),"Fuck This 2");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_champion, container, false);
        Log.v(String.valueOf(1),"Fuck This 3");
        recyclerView = view.findViewById(R.id.recyclerView);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CommunityDragonAPI theCall = retrofit.create(CommunityDragonAPI.class);
        Call<List<ChampionSimple>> allChampions = theCall.getAllChampions();
        allChampions.enqueue(new Callback<List<ChampionSimple>>() {
            @Override
            public void onResponse(Call<List<ChampionSimple>> call, Response<List<ChampionSimple>> response) {
                Log.v(String.valueOf(1), "Can't Retrieve the Database");
                if (response.code() != 200){
                    Log.v(String.valueOf(1), "Can't Retrieve the Database");
                } else {
                    championSimples = response.body();
                    championSimples.remove(0);
                    for (ChampionSimple simple : championSimples){
                        Log.v(String.valueOf(1), simple.getName());
                    }
                }
            }
            @Override
            public void onFailure(Call<List<ChampionSimple>> call, Throwable t) {
                Log.v(String.valueOf(1),"No clue what happened");
            }
        });

        PutDataIntoRecyclerView(championSimples);

        return view;
    }

    private void PutDataIntoRecyclerView(List<ChampionSimple> championSimples) {
        Log.v(String.valueOf(1),"Fuck This 4");
        SimpleChampionAdapter adapter = new SimpleChampionAdapter(context, championSimples);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

}