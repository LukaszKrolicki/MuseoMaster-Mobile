package eu.pl.snk.senseibunny.museomaster.models;

import android.content.Context;

import java.sql.SQLException;

public class Model {
    private static Model model;
    private DataBaseDriver dataBaseDriver;

    private Model() throws SQLException {

        this.dataBaseDriver = new DataBaseDriver();

    }

    public DataBaseDriver getDataBaseDriver() {
        return dataBaseDriver;
    }

    public static synchronized Model getInstance() throws SQLException {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

}