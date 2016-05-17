package group6.eda397_2016.chalmers.se.pinder.listhelpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import group6.eda397_2016.chalmers.se.pinder.R;
import group6.eda397_2016.chalmers.se.pinder.model.Task;

/**
 * Created by Tibor on 2016. 04. 26..
 */
public class TaskAdapter extends ArrayAdapter<Task> {
    private List<Task> objects;
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
        if(task != null){
            TextView title = (TextView)convertView.findViewById(R.id.listElemTitle);
            TextView description = (TextView)convertView.findViewById(R.id.listElemDescription);
            title.setText(task.getName());
            description.setText(task.getRequiredSkills().toString());
        }

        return convertView;
    }
}
