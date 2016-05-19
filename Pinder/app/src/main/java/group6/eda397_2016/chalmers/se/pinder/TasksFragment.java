package group6.eda397_2016.chalmers.se.pinder;

import android.support.design.widget.Snackbar;
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
import group6.eda397_2016.chalmers.se.pinder.model.Profile;
import group6.eda397_2016.chalmers.se.pinder.model.Task;

/**
 * Created by fredrikholmdahl on 16-04-21.
 */
public class TasksFragment extends Fragment{

    private View view;
    private static final String ARG_SECTION_NUMBER = "section_number";
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for login
        view = inflater.inflate(R.layout.fragment_tasks, container, false);
        listView = (ListView)view.findViewById(R.id.taskList);

        final Database db = ((PinderApplication) getActivity().getApplication()).getDatabase();
        List<Task> tasks = db.getMatchingTasks();

        final ArrayAdapter adapter = new TaskAdapter(getActivity(), R.layout.listelement, tasks);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Task item = (Task) parent.getItemAtPosition(position);

                //bool to see if you exist on the task already
                boolean exists = false;

                //if the task has 1 or 2 members
                if(item.getAssignedMembers().size() != 0 || item.getAssignedMembers().size() <= 2){
                    for (Profile p : item.getAssignedMembers()) {
                        //if you are on the assigned list
                        if(p.getName().toString().equals(db.getCurrentUser().getName().toString())){
                            exists = true;
                        }
                        //remains false if you are not assigned to task
                    }
                }


                if (exists){
                    Snackbar.make(view, "You are now removed from this task", Snackbar.LENGTH_LONG).show();
                    item.removeMember(db.getCurrentUser().getName().toString());
                    TrelloAPIConsumer.updateAssignedMembersForTask(getActivity().getApplicationContext(), item);
                }
                else if (!exists){
                    if (item.getAssignedMembers().size() == 2){
                        Toast.makeText(getActivity(), "This task is already assigned to a pair", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Snackbar.make(view, "You are now assigned to this task (click again to be removed)", Snackbar.LENGTH_LONG).show();

                        //add the user to the task here
                        item.assignMember(db.getCurrentUser());
                        TrelloAPIConsumer.updateAssignedMembersForTask(getActivity().getApplicationContext(), item);
                    }

                }
                adapter.notifyDataSetChanged();


                /* THE OLD CODE

                boolean result = item.assignMember(db.getCurrentUser());
                if (result) {
                    TrelloAPIConsumer.updateAssignedMembersForTask(getActivity().getApplicationContext(), item);
                    Toast.makeText(getActivity(), "Adding yourself to task", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "This task is already assigned to a pair", Toast.LENGTH_SHORT).show();
                }*/
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
