package eu.pl.snk.senseibunny.museomaster.models;

import android.content.Context;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Date;

import java.util.Objects;


public class Model {
    private static Model model;
    private DataBaseDriver dataBaseDriver;

    private Client client;

    //Admin
    private final ArrayList<Client> clients;

    private final ArrayList<Report> reports;
    ////////////////////////////////


    //Curator
    private final ArrayList<Exhibit> exhibits;

    private final ArrayList<Exhibition> exhibitions;

    private final ArrayList<String> allRooms;
    ////////////////////////////////


    //Normal worker
    private final ArrayList<Task> tasks;
    private final ArrayList<Task> tasks_finished;

    ////////////////////////////////////////////////


    private Model(Context context) throws SQLException {

        this.client = new Client(42, "", "", "", 0, 0, "x", 0, "x");

        //Admin
        this.dataBaseDriver = new DataBaseDriver(context);
        //Admin
        this.clients = new ArrayList<Client>();
        this.reports=new ArrayList<Report>();
        //Curator
        this.exhibits = new ArrayList<>();
        this.exhibitions = new ArrayList<>();
        this.allRooms = new ArrayList<>();

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



    //Curator Section
    public void setExhibits() {
        ResultSet resultSet = dataBaseDriver.getAllExhibitsData();
        try {
            while (resultSet.next()) {
                Integer idZabytku = resultSet.getInt("idEksponatu");
                String nazwaEksponatu = resultSet.getString("nazwaEksponatu");
                String okresPowstania = resultSet.getString("okresPowstania");
                String tematyka = resultSet.getString("tematyka");
                String tworca = resultSet.getString("twórca");
                String aktualMiejscePrzech = resultSet.getString("aktualMiejscePrzech");
                String opis = resultSet.getString("opis");
                Integer WystawaidWystawy = resultSet.getInt("WystawaidWystawy");
                Integer ZadanieidZadania = resultSet.getInt("ZadanieidZadania");
                Integer SalaidSali = resultSet.getInt("SalaidSali");
                Integer ZadaniePracownikidPracownika = resultSet.getInt("ZadaniePracownikidPracownika");
                exhibits.add(new Exhibit(idZabytku, nazwaEksponatu, okresPowstania, tematyka, tworca, aktualMiejscePrzech, opis,
                        WystawaidWystawy, ZadanieidZadania, SalaidSali, ZadaniePracownikidPracownika,""));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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


    public ArrayList<Exhibit> getExhibits() {
        return exhibits;
    }


    public ArrayList<Exhibition> getExhibitions() {
        return exhibitions;
    }

    public void setExhibitions() {
        ResultSet resultSet = dataBaseDriver.getAllExhibitionsData();

        try {
            while (resultSet.next()) {
                Integer idWystawy = resultSet.getInt("idWystawy");
                String nazwaWystawy = resultSet.getString("nazwaWystawy");
                String sala = resultSet.getString("sala");
                String miejsceWykonania = resultSet.getString("miejsceWykonania");
                String tematyka = resultSet.getString("tematyka");
                String tworca = resultSet.getString("tworca");
                Date dataRozpoczecia = resultSet.getDate("dataRozpoczecia");
                Date dataZakonczenia = resultSet.getDate("dataZakonczenia");
                exhibitions.add(new Exhibition(idWystawy, nazwaWystawy, sala, miejsceWykonania, tematyka, tworca, dataRozpoczecia, dataZakonczenia));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getAllRooms() {
        return allRooms;
    }

    public void setAllRooms() {
        ResultSet resultSet = dataBaseDriver.getAllRoomsNames();

        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("idSali");
                Integer wielksc = resultSet.getInt("wielkość");
                String nazwa = resultSet.getString("nazwa");
                String typ = resultSet.getString("typ");
                allRooms.add(nazwa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ////////////////////////////////////////////////////////////////

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