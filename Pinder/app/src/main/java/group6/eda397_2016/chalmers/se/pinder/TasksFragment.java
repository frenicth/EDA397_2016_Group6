package group6.eda397_2016.chalmers.se.pinder;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fredrikholmdahl on 16-04-21.
 */
public class TasksFragment extends Fragment{

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for login
        view = inflater.inflate(R.layout.fragment_tasks, container, false);
        return view;
    }




}
