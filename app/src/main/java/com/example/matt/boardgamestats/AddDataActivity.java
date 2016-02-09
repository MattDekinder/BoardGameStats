package com.example.matt.boardgamestats;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AddDataActivity extends AppCompatActivity{

    //The strings will be used to find which layout to use in the xml  TODO: find a better way to store this info
    //Don't change these strings.

    ArrayList<GameDataModel> gameTypes;
    GameDataModel game;
    ArrayList<PlayerModel> playerList;
    ArrayList<PlayerModel> scoreList;

    private static final String FILENAME = "file.sav";

    ArrayAdapter<String> arrayAdapter;
    ListAdapterPlayerModel playerListAdapter;
    ListAdapterPlayerScore playerScoreAdapter;
    ListAdapterGameModel gameListAdapter;
    ListView gameListView;
    ListView playerListView;
    ListView scoreListView;
    ViewFlipper vf;
    TextView title;

    protected void saveToFile(GameDataModel GDM){
        Gson gson = new Gson();
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            String jsonString = gson.toJson(GDM) + "\n";
            fos.write(jsonString.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected GameDataModel loadFromFile() {
        Gson gson = new Gson();
        GameDataModel GDM = null;


        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            GDM  = (gson.fromJson(line, GameDataModel.class));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GDM;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        scoreListView = (ListView) findViewById(R.id.player_scores);

        gameTypes = new ArrayList<GameDataModel>();
        gameTypes.add(new GameDataModel("Dominion"));
        gameTypes.add(new GameDataModel("Cribbage"));


        //TODO: make a list of players which is stored somewhere besides hardcoded here
        playerList = new ArrayList<PlayerModel>();
        scoreList = new ArrayList<PlayerModel>();
        final PlayerModel player1 = new PlayerModel("Matthew");
        PlayerModel player2 = new PlayerModel("Evan");
        PlayerModel player3 = new PlayerModel("Colin");
        PlayerModel player4 = new PlayerModel("Derek");
        PlayerModel player5 = new PlayerModel("Scott");
        PlayerModel player6 = new PlayerModel("Nick");
        PlayerModel player7 = new PlayerModel("Joe");
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        playerList.add(player4);
        playerList.add(player5);
        playerList.add(player6);
        playerList.add(player7);

        gameListView = (ListView) findViewById(R.id.game_list);
        vf = (ViewFlipper) findViewById( R.id.viewFlipper );
        title = (TextView) findViewById(R.id.title);

        gameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                GameDataModel gdm = (GameDataModel) gameListView.getItemAtPosition(position);
                game = gdm;
                vf.showNext(); //change to the next view.
               // gameListView.setOnItemClickListener(null); //deactivate listener-- is this necessary?

            }
        });

        playerListView = (ListView) findViewById(R.id.player_list);

        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // ListView Clicked item index
                PlayerModel player = (PlayerModel) playerListView.getItemAtPosition(position);
                player.toggleChecked();
                playerListAdapter.notifyDataSetChanged();
            }
        });

        Button finishPlayers = (Button) findViewById(R.id.finishPlayerSelect);
        finishPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < playerList.size(); i++) {
                    PlayerModel pm = playerList.get(i);

                    if (pm.getIsChecked() != 0) {
                        scoreList.add(pm);
                    }
                }
                playerScoreAdapter.notifyDataSetChanged();
                vf.showNext();
            }


        });

        scoreListView = (ListView) findViewById(R.id.player_scores);

        Button saveGame = (Button) findViewById(R.id.SaveGame);
        saveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<scoreListView.getAdapter().getCount(); i++) {
                    View viewScoreObject = scoreListView.getChildAt(i);
                    if (viewScoreObject.findViewById(R.id.score) != null) {
                        EditText edit = (EditText) viewScoreObject.findViewById(R.id.score);
                        Log.d("Adding scores:", edit.getText().toString());
                        scoreList.get(i).setScore(Integer.parseInt(edit.getText().toString()));
                    }
                }
                game.setPlayers(scoreList);
                saveToFile(game);
                Log.d("Saving","saving call has happened");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_data, menu);
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

    @Override
    protected void onResume() {
        super.onResume();
        gameListAdapter = new ListAdapterGameModel(this, gameTypes);
        playerListAdapter = new ListAdapterPlayerModel(this, playerList);
        playerScoreAdapter = new ListAdapterPlayerScore(this,scoreList);

        gameListView.setAdapter(gameListAdapter);
        playerListView.setAdapter(playerListAdapter);
        scoreListView.setAdapter(playerScoreAdapter);

    }
}
