package eu.pl.snk.senseibunny.museomaster.models;

import android.content.Context;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Model {
    private static Model model;
    private DataBaseDriver dataBaseDriver;

    //Admin
    private final ArrayList<Client> clients;

    private final ArrayList<Report> reports;
    ////////////////////////////////

    //Curator
    private final ArrayList<Exhibit> exhibits;

    private final ArrayList<Exhibition> exhibitions;
    ////////////////////////////////

    private Model(Context context) throws SQLException {

        this.dataBaseDriver = new DataBaseDriver(context);
        //Admin
        this.clients = new ArrayList<Client>();
        this.reports=new ArrayList<Report>();
        //Curator
        this.exhibits = new ArrayList<>();
        this.exhibitions = new ArrayList<>();

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

    ////////////////////////////////////////////////////////////////

}