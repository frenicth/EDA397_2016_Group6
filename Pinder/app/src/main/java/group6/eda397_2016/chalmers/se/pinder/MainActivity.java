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
import android.widget.Button;
import android.widget.TextView;
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

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Do we need to add tasks inside the app?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
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
            Toast.makeText(MainActivity.this, "Logging out...", Toast.LENGTH_SHORT).show();
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

            /*Button buttonTasks = (Button) findViewById(R.id.taskTab);
            Button buttonProfiles = (Button) findViewById(R.id.usersTab);*/
            switch (position) {
                case 0:
                    //buttonTasks.setTextColor(getResources().getColor(R.color.trelloBlue));
                    //buttonProfiles.setTextColor(getResources().getColor(R.color.white));

                    return ProfilesFragment.newInstance();
                case 1:
                    //buttonProfiles.setTextColor(getResources().getColor(R.color.trelloBlue));
                    //buttonTasks.setTextColor(getResources().getColor(R.color.white));

                    return TasksFragment.newInstance();
                /*case 2:
                    return UserProfileFragment.newInstance();*/
                default:
                    return ProfilesFragment.newInstance();
            }
        }

        //TODO get the active fragment and change attributes on the tabbed buttons depending on the active fragment
        //TODO -||-                    and reload the fragments content on active

        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

    }
}