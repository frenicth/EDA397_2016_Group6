package group6.eda397_2016.chalmers.se.pinder;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import group6.eda397_2016.chalmers.se.pinder.dao.Database;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getFragmentManager();
    MainFragment mainFragment;
    ProfilesFragment profilesFragment;
    FragmentTransaction fragmentTransaction;
    TaskFragment taskFragment;
    UserProfileFragment userProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();
        profilesFragment = new ProfilesFragment();
        taskFragment = new TaskFragment();
        userProfileFragment = new UserProfileFragment();
        fragmentManager.beginTransaction().add(R.id.activity_main, mainFragment).commit();

    }
    public void showTasks (View view ) {
        Database database = ((PinderApplication)getApplication()).getDatabase();
        Log.i(this.getClass().getName(), database.getAllTasks().size() + "");

        fragmentTransaction= getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, taskFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    public void viewProfile(View view){
        //view you own profile, get data from database and show in fragment used below
        fragmentTransaction= getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, userProfileFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }
    public void showProfiles(View view){
        //view list of profiles, get data from database and show in fragment used below
        fragmentTransaction= getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, profilesFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    //back to main fragment
    public void backToMain(View view){
        fragmentTransaction= getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, mainFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    //back to list of tasks when viewing a single task
    public void backToTasks(View view){
        fragmentTransaction= getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main, taskFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

}