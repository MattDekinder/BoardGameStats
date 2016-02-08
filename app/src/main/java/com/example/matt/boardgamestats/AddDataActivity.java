package com.example.matt.boardgamestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddDataActivity extends AppCompatActivity{

    //The strings will be used to find which layout to use in the xml  TODO: find a better way to store this info
    //Don't change these strings.

    List<String> gameTypes = Arrays.asList("Dominion", "Cribbage");
    ArrayList<PlayerModel> playerList;
    ArrayList<PlayerModel> scoreList;


    ArrayAdapter<String> arrayAdapter;
    ListAdapterPlayerModel playerListAdapter;
    ListAdapterPlayerScore playerScoreAdapter;
    ListView gameListView;
    ListView playerListView;
    ListView scoreListView;
    ViewFlipper vf;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        scoreListView = (ListView) findViewById(R.id.player_scores);

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
                // ListView Clicked item value
                String itemValue = (String) gameListView.getItemAtPosition(position);
                //TODO: make this an actual game model and assign it to a variable to use in the end part of this activity
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "ListItem : " + itemValue, Toast.LENGTH_SHORT)
                        .show();

               // title.setText(itemValue); //this is the title of the next screen in this activity

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
                Toast.makeText(getApplicationContext(), "Name: " +
                        player.getName() + " Checked: " + String.valueOf(player.getIsChecked()), Toast.LENGTH_SHORT)
                        .show();
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


        Button saveGame = (Button) findViewById(R.id.SaveGame);
        finishPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        //populate the list of selectable games
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, gameTypes);

        playerListAdapter = new ListAdapterPlayerModel(this, playerList);
        playerScoreAdapter = new ListAdapterPlayerScore(this,scoreList);

        gameListView.setAdapter(arrayAdapter);
        playerListView.setAdapter(playerListAdapter);
        scoreListView.setAdapter(playerScoreAdapter);

    }
}
