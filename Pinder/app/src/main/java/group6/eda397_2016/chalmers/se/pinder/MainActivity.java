package group6.eda397_2016.chalmers.se.pinder;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import group6.eda397_2016.chalmers.se.pinder.TrelloInteraction.TrelloAPIConsumer;
import group6.eda397_2016.chalmers.se.pinder.dao.Database;



public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getFragmentManager();
    MainFragment mainFragment;
    ProfilesFragment profilesFragment;
    FragmentTransaction fragmentTransaction;
    TasksFragment tasksFragment;
    UserProfileFragment userProfileFragment;
    public android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Database database = ((PinderApplication)getApplication()).getDatabase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        mainFragment = new MainFragment();
        profilesFragment = new ProfilesFragment();
        tasksFragment = new TasksFragment();
        userProfileFragment = new UserProfileFragment();
        database.clearDB();
        TrelloAPIConsumer.fetchUserProfile("me", this.getApplicationContext());
        TrelloAPIConsumer.fetchTeamMembers(this.getApplicationContext());
        TrelloAPIConsumer.fetchBackLogTasks(this.getApplicationContext());
        fragmentManager.beginTransaction().add(R.id.activity_main, mainFragment).commit();


    }
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                fragmentManager.popBackStack();
                actionBar.setDisplayHomeAsUpEnabled(false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showTasks (View view ) {
        Database database = ((PinderApplication)getApplication()).getDatabase();
        Log.i(this.getClass().getName(), database.getAllTasks().size() + "");

        fragmentTransaction= getFragmentManager().beginTransaction();
        //fragmentTransaction.replace(R.id.activity_main, tasksFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void viewProfile(View view){
        //view you own profile, get data from database and show in fragment used below
        fragmentTransaction= getFragmentManager().beginTransaction();
        //fragmentTransaction.replace(R.id.activity_main, userProfileFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    public void showProfiles(View view){

        //view list of profiles, get data from database and show in fragment used below
        fragmentTransaction= getFragmentManager().beginTransaction();
        //fragmentTransaction.replace(R.id.activity_main, profilesFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    public void signOut(View view){
        SharedPreferences sharedPreferences = this.getSharedPreferences("authorizeprefs", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("authtoken", "empty").apply();
        Toast.makeText(MainActivity.this, "You are now logged out", Toast.LENGTH_SHORT).show();
        Intent log = new Intent(this, LoginActivity.class);
        startActivity(log);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
            actionBar.setDisplayHomeAsUpEnabled(false);
        } else {
            //we don't want back navigation to go back to the login screen, sign out should do be the only navigation back to
            //that screen.
            //super.onBackPressed();
        }
    }
    */

}