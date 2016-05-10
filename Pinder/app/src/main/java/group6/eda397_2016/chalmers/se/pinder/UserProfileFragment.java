package group6.eda397_2016.chalmers.se.pinder;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import group6.eda397_2016.chalmers.se.pinder.model.Task;

/**
 * Created by fredrikholmdahl on 16-04-21.
 */
public class UserProfileFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for login
        view = inflater.inflate(R.layout.fragment_userprofile, container, false);
        List<Task> tasks = ((PinderApplication)getActivity().getApplication()).getDatabase().getMatchingTasks(null);
        for (Task t : tasks){
            Log.i(this.getClass().getName(), t.toString());
        }
        return view;
    }
}
