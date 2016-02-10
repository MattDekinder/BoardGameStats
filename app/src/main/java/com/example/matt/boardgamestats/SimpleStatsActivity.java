package com.example.matt.boardgamestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class SimpleStatsActivity extends RootActivity {

    ListView vi;
    ListAdapterGameModel adapter;
    ArrayList<GameDataModel> dataList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_stats_activity);
        dataList = new ArrayList<GameDataModel>();
        vi = (ListView) findViewById(R.id.recent_games_list);
        Button newGame = (Button) findViewById(R.id.new_game);

            //set a listener for the list of games
            vi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    GameDataModel gdm = (GameDataModel) vi.getItemAtPosition(position);

                    Intent i = new Intent(getApplicationContext(), DetailedStatsActivity.class);
                    gdm.setFlag(1); //distinguishes the model instance to bring up stats for TODO: probably better to just pass the model to the new activity
                    startActivity(i);
                }
            });

            //set listener for button
            newGame.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), AddDataActivity.class);
                    startActivity(i);
                }
            });




    }

    @Override
    protected void onResume()
    {
        super.onResume();
        adapter = new ListAdapterGameModel(this, dataList);
        vi.setAdapter(adapter);
        GameDataModel gdm = loadFromFile();
        if (gdm != null){
            dataList.add(gdm);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}



