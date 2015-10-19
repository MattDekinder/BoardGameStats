package com.example.matt.boardgamestats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Arrays;
import java.util.List;

public class AddDataActivity extends AppCompatActivity{

    //The strings will be used to find which layout to use in the xml  TODO: find a better way to store this info
    //Don't change these strings.
    List<String> gameTypes = Arrays.asList("Dominion", "Cribbage");

    ArrayAdapter<String> adapter;
    ListView vi;
    ViewFlipper vf;
    TextView title;
    CheckedTextView checkPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        vi = (ListView) findViewById(R.id.game_list);
        vf = (ViewFlipper) findViewById( R.id.viewFlipper );
        title = (TextView) findViewById(R.id.title);
        checkPlayers = (CheckedTextView) findViewById(R.id.checkPlayers);

        vi.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) vi.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

                title.setText(itemValue); //this is the title of the next screen in this activity
                //TODO: create a list of player models and a list adapter for them.

                vf.showNext(); //change to the next view.

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
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, gameTypes);

        vi.setAdapter(adapter);



    }
}
