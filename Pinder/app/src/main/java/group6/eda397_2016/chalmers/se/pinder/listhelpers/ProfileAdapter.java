package group6.eda397_2016.chalmers.se.pinder.listhelpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import group6.eda397_2016.chalmers.se.pinder.R;
import group6.eda397_2016.chalmers.se.pinder.model.Profile;


public class ProfileAdapter extends ArrayAdapter<Profile>{
    private List<Profile> objects;
    public ProfileAdapter(Context context, int resource, List<Profile> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listelement, null);
        }
        Profile profile = objects.get(position);
        if(profile!=null){
            TextView title = (TextView)convertView.findViewById(R.id.listElemTitle);
            TextView skillset = (TextView)convertView.findViewById(R.id.listElemDescription);
            title.setText(profile.getName());
            String skills = "";
            if (!profile.getSkills().isEmpty())
            {
                for(String s:profile.getSkills())
                {
                    skills+=s +"  ";
                }
            }
            skillset.setText(skills);
        }
        return convertView;
    }
}
