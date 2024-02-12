package eu.pl.snk.senseibunny.museomaster.models;

import android.content.Context;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Model {
    private static Model model;
    private DataBaseDriver dataBaseDriver;

    private Client client;

    //Admin
    private final ArrayList<Client> clients;

    private final ArrayList<Report> reports;
    ////////////////////////////////


    //Normal worker
    private final ArrayList<Task> tasks;
    private final ArrayList<Task> tasks_finished;

    ////////////////////////////////////////////////

    private Model(Context context) throws SQLException {

        this.client = new Client(15, "", "", "", 0, 0, "x", 0, "x");

        //Admin
        this.dataBaseDriver = new DataBaseDriver(context);
        this.clients = new ArrayList<Client>();
        this.reports=new ArrayList<Report>();

        //Normal worker
        this.tasks = new ArrayList<Task>();
        this.tasks_finished=new ArrayList<Task>();

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

    public static synchronized Model getInstanceWC() throws SQLException {

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

    public ArrayList<Report> getReports() {
        return reports;
    }

    public void setReports() {
        ResultSet resultSet = dataBaseDriver.getAllReporstData();

        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("idPracownika");
                String nazwaUzytkownika = resultSet.getString("username");
                String opis = resultSet.getString("opis");
                Integer idR = resultSet.getInt("idRaportu");
                reports.add(new Report(id, idR, nazwaUzytkownika, opis));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ////////////////////////////////////////////////////////////////


    //Normal worker section *************************************************************************************************************
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> getFishedTasks() {
        return tasks_finished;
    }

    public void setTasks(String type) {
        ResultSet resultSet;
        if (Objects.equals(type, "assigned")) {
            resultSet = dataBaseDriver.getAssignedTask(client.getIdPracownika());
        } else if (Objects.equals(type, "assignedTo")) {
            resultSet = dataBaseDriver.getAssignedTaskToLv(client.getNazwaUzytkownika());
        } else {
            resultSet = dataBaseDriver.getFinishedTask(client.getIdPracownika());
        }

        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("idZadania");
                String temat = resultSet.getString("temat");
                String opis = resultSet.getString("opis");
                String dataRozpoczecia = resultSet.getDate("dataRozpoczęcia").toString();
                String dataZakonczenia = resultSet.getDate("dataZakończenia").toString();
                String status = resultSet.getString("status");
                Integer idPracownika = resultSet.getInt("idPracownika");
                String nazwaUzytkownikaNadajacego = resultSet.getString("nazwaNadajacego");
                String nazwaUzytkownika = resultSet.getString("nazwaUzytkownika");
                if (Objects.equals(type, "assigned")) {
                    tasks.add(0, new Task(id, temat, opis, dataRozpoczecia, dataZakonczenia, status, idPracownika, nazwaUzytkownikaNadajacego, nazwaUzytkownika));
                } else if (Objects.equals(type, "assignedTo")) {
                    //tasksAssignedTo.add(0, new Zadanie(id, temat, opis, dataRozpoczecia, dataZakonczenia, status, idPracownika, nazwaUzytkownikaNadajacego, nazwaUzytkownika));
                } else {
                    tasks_finished.add(0, new Task(id, temat, opis, dataRozpoczecia, dataZakonczenia, status, idPracownika, nazwaUzytkownikaNadajacego, nazwaUzytkownika));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearTasks() {
        tasks.clear();
    }

    public void clearFinishedTasks() {
        tasks_finished.clear();
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        tasks_finished.add(0, task);
    }

    public Client getClient() {
        return client;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

}