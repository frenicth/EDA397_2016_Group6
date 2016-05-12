package group6.eda397_2016.chalmers.se.pinder;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import group6.eda397_2016.chalmers.se.pinder.TrelloInteraction.TrelloAPIConsumer;
import group6.eda397_2016.chalmers.se.pinder.dao.Database;
import group6.eda397_2016.chalmers.se.pinder.model.Profile;

public class Main2Activity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    android.app.FragmentManager fragmentManager = getFragmentManager();
    //MainFragment mainFragment;
    ProfilesFragment profilesFragment;
    FragmentTransaction fragmentTransaction;
    TasksFragment tasksFragment;
    UserProfileFragment userProfileFragment;
    public android.support.v7.app.ActionBar actionBar;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Database database = ((PinderApplication)getApplication()).getDatabase();
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main2);
        //mainFragment = new MainFragment();
        //userProfileFragment = new UserProfileFragment();
        //tasksFragment = new TasksFragment();
        //profilesFragment = new ProfilesFragment();
        database.clearDB();
        TrelloAPIConsumer.fetchUserProfile("me", this.getApplicationContext());
        TrelloAPIConsumer.fetchTeamMembers(this.getApplicationContext());
        TrelloAPIConsumer.fetchBackLogTasks(this.getApplicationContext());


        setContentView(R.layout.activity_main2);

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
    /*public void signOut(View view){
        SharedPreferences sharedPreferences = this.getSharedPreferences("authorizeprefs", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("authtoken", "empty").apply();
        Toast.makeText(Main2Activity.this, "You are now logged out", Toast.LENGTH_SHORT).show();
        Intent log = new Intent(this, LoginActivity.class);
        startActivity(log);
    }*/

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
            Toast.makeText(Main2Activity.this, "You are now logged out", Toast.LENGTH_SHORT).show();
            Intent log = new Intent(this, LoginActivity.class);
            startActivity(log);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }*/

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            System.out.println(position);
            switch(position){
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
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }*/
    }

}
