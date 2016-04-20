package group6.eda397_2016.chalmers.se.pinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import group6.eda397_2016.chalmers.se.pinder.dao.Database;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Database database = ((PinderApplication)getApplication()).getDatabase();
        Log.i(this.getClass().getName(), database.getAllTasks().size() + "");
    }
}
