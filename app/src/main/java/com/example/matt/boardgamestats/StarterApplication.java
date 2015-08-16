package com.example.matt.boardgamestats;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseUser;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(this, "T3tC3zk8lLXDEXYkYNfpIrEU0aiz0AcAfAjNn4lz", "X3FmqGVs8pHLTj7mYJbOhnkTVtV7g7OPavlg15Oh");
    //ParseInstallation.getCurrentInstallation().saveInBackground();

    //ParseUser.enableAutomaticUser();
    //ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    //ParseACL.setDefaultACL(defaultACL, true);
  }
}
