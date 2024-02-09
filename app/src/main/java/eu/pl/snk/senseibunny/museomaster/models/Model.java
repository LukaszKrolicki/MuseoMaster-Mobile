package eu.pl.snk.senseibunny.museomaster.models;

import android.content.Context;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
    private static Model model;
    private DataBaseDriver dataBaseDriver;

    //Admin
    private final ArrayList<Client> clients;
    ////////////////////////////////

    private Model(Context context) throws SQLException {

        this.dataBaseDriver = new DataBaseDriver(context);
        this.clients = new ArrayList<Client>();

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


    //Admin Section ********************************
    public void setClients() {
        ResultSet resultSet = dataBaseDriver.getAllClientsData();

        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("idPracownika");
                String imie = resultSet.getString("imie");
                String nazwisko = resultSet.getString("nazwisko");
                String nazwaUzytkownika = resultSet.getString("nazwaUżytkownika");
                String email = resultSet.getString("e-mail");
                Integer nrTelefonu = resultSet.getInt("nrTelefonu");
                Integer wiek = resultSet.getInt("wiek");
                Integer uprawniony = resultSet.getInt("czyUprawniony");
                String rola = resultSet.getString("rola");
                clients.add(new Client(id, imie, nazwisko, email, wiek, uprawniony, rola, nrTelefonu, nazwaUzytkownika));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    ////////////////////////////////////////////////////////////////

}