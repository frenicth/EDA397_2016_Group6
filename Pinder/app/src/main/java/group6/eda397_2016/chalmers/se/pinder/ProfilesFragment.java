package group6.eda397_2016.chalmers.se.pinder;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import group6.eda397_2016.chalmers.se.pinder.listhelpers.ProfileAdapter;
import group6.eda397_2016.chalmers.se.pinder.model.Profile;

/**
 * Created by fredrikholmdahl on 16-04-21.
 */
public class ProfilesFragment extends Fragment {

    private View view;
    private static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        // Inflate the layout for login
        view = inflater.inflate(R.layout.fragment_profiles, container, false);
        ListView listView = (ListView)view.findViewById(R.id.profileList);
        List<Profile> profiles = ((PinderApplication)getActivity().getApplication()).getDatabase().getAllProfiles();


        final ArrayAdapter adapter = new ProfileAdapter(getActivity(),R.layout.listelement, profiles);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Profile item = (Profile) parent.getItemAtPosition(position);
                Log.i(getClass().getName(),item.toString());
            }
        });

        return view;
    }
    public static ProfilesFragment newInstance() {
        ProfilesFragment fragment = new ProfilesFragment();
        //Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        //fragment.setArguments(args);
        return fragment;
    }

}
