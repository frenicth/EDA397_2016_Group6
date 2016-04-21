package group6.eda397_2016.chalmers.se.pinder;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import group6.eda397_2016.chalmers.se.pinder.dao.Database;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getFragmentManager();
    MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();

        fragmentManager.beginTransaction().add(R.id.activity_main, mainFragment).commit();

    }
    public void showTasks (View view ) {
        Database database = ((PinderApplication)getApplication()).getDatabase();
        Log.i(this.getClass().getName(), database.getAllTasks().size() + "");

    }

    public void viewProfile(View view){
        //view you own profile, get data from database
    }
    public void showProfiles(View view){
        //view list of profiles, get data from database
    }
}