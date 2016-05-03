package group6.eda397_2016.chalmers.se.pinder.temp;

import android.util.Log;

import java.util.ArrayList;
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
        //List<Skill> skills = new ArrayList<>();
        //skills.add(Skill.CPlusPlus);
        //skills.add(Skill.Java);
        //profiles.add(new Profile(0,"John Doe",skills1));
        //profiles.add(new Profile(1,"Jane Doe",new ArrayList<Skill>()));
        //tasks.add(new Task(0,"Task 1","The first task",5));
        //tasks.add(new Task(1,"Task 2","The second task",3));
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

    @Override
    public Task getTaskById(int id) {
        return null;
    }

    @Override
    public Profile getProfileById(int id) {
        return null;
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
    public List<Task> getMatchingTasks(Task t){
        List<Task> matchingTasks = new ArrayList<>();
        List<Profile> users = this.getAllProfiles();
        List<Task> tasks = this.getAllTasks();
        for(Task task : tasks){
            for(String requiredSkills : task.getRequiredSkills()){
                for(String skill : this.currentUser.getSkills()){
                    if(skill.equals(requiredSkills)) {
                        System.out.println("We have a match");
                        /**
                         * TODO: Return this task
                         * This is a task which required skills matches the
                         * current users skills.
                         */
                    }
                }
            }
        }

        return matchingTasks;
    }
}
