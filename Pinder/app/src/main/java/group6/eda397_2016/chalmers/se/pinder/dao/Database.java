package group6.eda397_2016.chalmers.se.pinder.dao;


import java.util.List;

import group6.eda397_2016.chalmers.se.pinder.model.Profile;
import group6.eda397_2016.chalmers.se.pinder.model.Task;

/**
 * DAO stands for Data Access Object. This interface is responsible for handling the database.
 * It contains basic methods which might be required for reading from
 * or inserting new elements into the database.
 */
public interface Database {
     public List<Task> getAllTasks();
     public List<Profile> getAllProfiles();
     public void createProfile(Profile profile);
     public void createTask(Task task);
     public void assignProfilesToTask(List<Profile> profiles, Task task);
     public Task getTaskById(String id);
     public Profile getProfileById(String id);
     public void setCurrentUser(Profile profile);
     public Profile getCurrentUser();
     public void clearDB();
     public List<Task> getMatchingTasks();
}
