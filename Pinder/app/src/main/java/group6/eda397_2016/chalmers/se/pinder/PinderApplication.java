package group6.eda397_2016.chalmers.se.pinder;

import android.app.Application;

import group6.eda397_2016.chalmers.se.pinder.dao.Database;
import group6.eda397_2016.chalmers.se.pinder.temp.DatabaseLocal;

public class PinderApplication extends Application {
    private Database database;
    @Override
    public void onCreate() {
        super.onCreate();
        database = DatabaseLocal.getInstance();
    }

    public Database getDatabase() {
        return database;
    }
}
