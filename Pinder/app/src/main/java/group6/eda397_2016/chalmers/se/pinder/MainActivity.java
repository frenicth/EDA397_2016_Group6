package group6.eda397_2016.chalmers.se.pinder;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
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
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Database database = ((PinderApplication)getApplication()).getDatabase();
        super.onCreate(savedInstanceState);
        /* OLD since tabbed navigation was implemented
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        mainFragment = new MainFragment();
        profilesFragment = new ProfilesFragment();
        tasksFragment = new TasksFragment();
        userProfileFragment = new UserProfileFragment();
        */
        database.clearDB();
        TrelloAPIConsumer.fetchUserProfile("me", this.getApplicationContext());
        TrelloAPIConsumer.fetchTeamMembers(this.getApplicationContext());
        TrelloAPIConsumer.fetchBackLogTasks(this.getApplicationContext());
        /* OLD since tabbed navigation was implemented
        fragmentManager.beginTransaction().add(R.id.activity_main, mainFragment).commit();
        */
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Do we need to add tasks inside the app?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            SharedPreferences sharedPreferences = this.getSharedPreferences("authorizeprefs", Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("authtoken", "empty").apply();
            Toast.makeText(MainActivity.this, "You are now logged out", Toast.LENGTH_SHORT).show();
            Intent log = new Intent(this, LoginActivity.class);
            startActivity(log);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            System.out.println(position);
            switch (position) {
                case 0:
                    return UserProfileFragment.newInstance();
                case 1:
                    return TasksFragment.newInstance();
                case 2:
                    return ProfilesFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
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
}