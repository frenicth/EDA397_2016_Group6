package group6.eda397_2016.chalmers.se.pinder;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import group6.eda397_2016.chalmers.se.pinder.TrelloInteraction.TrelloAPIConsumer;
import group6.eda397_2016.chalmers.se.pinder.dao.Database;
import group6.eda397_2016.chalmers.se.pinder.listhelpers.TaskAdapter;
import group6.eda397_2016.chalmers.se.pinder.model.Task;

/**
 * Created by fredrikholmdahl on 16-04-21.
 */
public class TasksFragment extends Fragment{

    private View view;
    private static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for login
        view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ListView listView = (ListView)view.findViewById(R.id.taskList);
        final Database db = ((PinderApplication) getActivity().getApplication()).getDatabase();
        List<Task> tasks = db.getMatchingTasks();
        final ArrayAdapter adapter = new TaskAdapter(getActivity(), R.layout.listelement, tasks);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Task item = (Task) parent.getItemAtPosition(position);
                boolean result =item.assignMember(db.getCurrentUser());
                if (result) {
                    TrelloAPIConsumer.updateAssignedMembersForTask(getActivity().getApplicationContext(), item);
                }
                else
                    Toast.makeText(getActivity(), "This task is already assigned to a pair", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public static TasksFragment newInstance() {
        TasksFragment fragment = new TasksFragment();
        //Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        //fragment.setArguments(args);
        return fragment;
    }




}
