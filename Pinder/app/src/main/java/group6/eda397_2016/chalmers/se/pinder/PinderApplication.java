package group6.eda397_2016.chalmers.se.pinder;

import android.app.Application;

import group6.eda397_2016.chalmers.se.pinder.TrelloInteraction.VolleyManager;
import group6.eda397_2016.chalmers.se.pinder.dao.Database;
import group6.eda397_2016.chalmers.se.pinder.temp.DatabaseLocal;

/**
 * This class is required to be able to have proper singleton or static classes.
 * Since Android's classes have a weird lifecycle, unlike in Java,
 * a static variable might not even be static after all, therefore we extend the Application class,
 * whose onCreate method will be called when the application gets launched.
 * This way we create an instance of the mock database when the application start running.
 * The reason why the database object is stored here is that, this way after the actual
 * permanent database is created, it will be much easier to change the mock database to the final
 * one, since we only have to do it in this class.
 *
 */
public class PinderApplication extends Application {
    private Database database;
    //private VolleyManager volleyManager;
    @Override
    public void onCreate() {
        super.onCreate();
        database = DatabaseLocal.getInstance();
       // volleyManager = VolleyManager.getInstance(this.getApplicationContext());
    }

public Database getDatabase() {
        return database;
    }

    //public VolleyManager getVolleyManager() {  return volleyManager; }

}

