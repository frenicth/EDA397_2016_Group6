package group6.eda397_2016.chalmers.se.pinder.temp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import group6.eda397_2016.chalmers.se.pinder.dao.Database;
import group6.eda397_2016.chalmers.se.pinder.model.Profile;
import group6.eda397_2016.chalmers.se.pinder.model.Skill;
import group6.eda397_2016.chalmers.se.pinder.model.Task;


/**
 * An implementation of the Database interface. Provides a runtime storage for the entities of the
 * application. This class is actually a singleton, which means only one instance will exist while
 * the application is opened.
 */
public class DatabaseLocal implements Database {
    private static volatile DatabaseLocal instance;
    private volatile List<Profile> profiles;
    private volatile List<Task> tasks;
    private volatile Profile currentUser;

    private DatabaseLocal() {
        profiles = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    public synchronized static DatabaseLocal getInstance() {
        if (instance == null) {
            instance = new DatabaseLocal();
        }
        return instance;
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profiles;
    }

    @Override
    public void createProfile(Profile profile) {
        profiles.add(profile);
        Log.d("DatabaseLocal class", "added profile with id: " + profile.getId());
    }

    @Override
    public void createTask(Task task) {
        tasks.add(task);
        Log.d("DatabaseLocal class", "added task with id: " + task.getId());
    }

    @Override
    public void assignProfilesToTask(List<Profile> profiles, Task task) {
        //TODO
    }


    public Task getTaskById(String id)
    {
        Task task = null;
        for (Task t:tasks)
        {
            if (t.getId().equals(id))
            {
                task=t;
            }
        }
        return task;
    }


    public Profile getProfileById(String id)
    {
        Profile profile = null;
        for (Profile p:profiles)
        {
            if (p.getId().equals(id))
            {
                profile=p;
            }
        }
        return profile;
    }

    public void setCurrentUser(Profile profile) {
        this.currentUser = profile;
        Log.d("DatabaseLocal class", "set current User: " + profile.getName());
    }

    public Profile getCurrentUser() {
        return this.currentUser;
    }

    public List<Profile> getMatchingProfiles(Task task) {
        List<Profile> matchingProfiles = new ArrayList<>();
        List<String> requiredSkills = task.getRequiredSkills();

        for (String skill : requiredSkills) {
            for (Profile user : this.getAllProfiles()) {
                for (String profileSkill : user.getSkills()) {
                    if (profileSkill.equals(skill)) {
                        System.out.println("We have a match");
                        /**
                         * TODO: Return this profile
                         * This is a profile which skills matches the
                         * tasks required skills.
                         */
                    }
                }
            }
        }
        return null;
    }
    public List<Task> getMatchingTasks(){
        List<Task> tasks = this.getAllTasks();

            Collections.sort(tasks, new Comparator<Task>() {
                @Override
                public int compare(Task lhs, Task rhs) {
                    return rateTask(lhs) - rateTask(rhs);
                }
            });

        return tasks;
    }
    private int rateTask(Task task){
        int counter = 0;
        for(String requiredSkill : task.getRequiredSkills()){
            for(String skill : this.currentUser.getSkills()){
                if(skill.trim().equals(requiredSkill.trim())) {
                    System.out.println("We have a match");
                    counter++;
                    /**
                     * TODO: Return this task
                     * This is a task which required skills matches the
                     * current users skills.
                     */
                }
            }
        }
        return counter;
    }

    public void clearDB()
    {
        this.tasks.clear();
        this.profiles.clear();
        this.currentUser = null;
    }

}
