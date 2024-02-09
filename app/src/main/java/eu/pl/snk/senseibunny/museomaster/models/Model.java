package eu.pl.snk.senseibunny.museomaster.models;

import android.content.Context;

import java.sql.SQLException;

public class Model {
    private static Model model;
    private DataBaseDriver dataBaseDriver;

    private Model(Context context) throws SQLException {

        this.dataBaseDriver = new DataBaseDriver(context);

    }

    public DataBaseDriver getDataBaseDriver() {
        return dataBaseDriver;
    }

    public static synchronized Model getInstance(Context context) throws SQLException {
        if (model == null) {
            model = new Model(context);
        }
        return model;
    }

}