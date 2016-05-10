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
     List<Task> getAllTasks();
     List<Profile> getAllProfiles();
     void createProfile(Profile profile);
     void createTask(Task task);
     void assignProfilesToTask(List<Profile> profiles, Task task);
     Task getTaskById(String id);
     Profile getProfileById(String id);
     void setCurrentUser(Profile profile);
     Profile getCurrentUser();
     void clearDB();
}
