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
import group6.eda397_2016.chalmers.se.pinder.model.Task;

/**
 * Created by Tibor on 2016. 04. 26..
 */
public class TaskAdapter extends ArrayAdapter<Task> {
    private List<Task> objects;
    private List<Profile> profiles;
    public TaskAdapter(Context context, int resource, List<Task> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listelement, null);
        }
        Task task = objects.get(position);
        //profiles are the members of the task, in list form
        profiles = task.getAssignedMembers();
        if(task != null){
            TextView title = (TextView)convertView.findViewById(R.id.listElemTitle);
            TextView description = (TextView)convertView.findViewById(R.id.listElemDescription);
            TextView users = (TextView)convertView.findViewById(R.id.listElemUsers);
            title.setText(task.getName());
            description.setText(task.getRequiredSkills().toString());

            //only sets the TextView for users if there are 1 or 2 users present
            if(profiles.size() != 0 || profiles.size() <= 2) {

                //index is just used for adding commas if needed
                int index = 0;
                String s = "";
                //concatinates the string s which is used to set the TextViews text to the members of the task
                for (Profile p : profiles) {
                    if(index > 0) {
                        s += ", " + p.getName();
                        index++;
                    }else{
                        s += p.getName();
                        index++;
                    }
                }
                users.setText(s);
            }
        }

        return convertView;
    }
}
