package group6.eda397_2016.chalmers.se.pinder;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fredrikholmdahl on 16-04-20.
 */
public class LoginFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for login
        view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }
}
