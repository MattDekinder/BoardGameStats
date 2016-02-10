package com.example.matt.boardgamestats;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by Matt on 2/9/2016.
 */
public abstract class RootActivity extends AppCompatActivity {

    public static final String SINGLEGAME = "file.sav";
    public static final String LISTOFGAMES = "list.sav";
    public static final String LISTOFGAMEREF = "refs.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize firebase
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://torrid-torch-7481.firebaseio.com/");

        //read data back
        myFirebaseRef.child("GAME").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Gson gson = new Gson();
                String JSONString = (String) snapshot.getValue();
                GameDataModel GDM  = (gson.fromJson(JSONString, GameDataModel.class));
                saveToFile(GDM);
                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }

        });

/*
        //create a user
        myFirebaseRef.createUser("test@fake.com", "thebestpassword", new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                // there was an error
            }
        });
*/
    }

    public void saveToFile(GameDataModel GDM){
        Gson gson = new Gson();
        try {
            FileOutputStream fos = openFileOutput(SINGLEGAME,
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

    public void saveToFirebase (GameDataModel GDM, String Name){
        Gson gson = new Gson();
        String jsonString = gson.toJson(GDM);
        //write data
        Firebase myFirebaseRef = new Firebase("https://torrid-torch-7481.firebaseio.com/");
        myFirebaseRef.child(Name).setValue(jsonString);
    }

    public GameDataModel loadFromFile() {
        Gson gson = new Gson();
        GameDataModel GDM = null;
        try {
            FileInputStream fis = openFileInput(SINGLEGAME);
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
}
