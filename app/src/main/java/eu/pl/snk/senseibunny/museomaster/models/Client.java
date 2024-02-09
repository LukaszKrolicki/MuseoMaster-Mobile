package eu.pl.snk.senseibunny.museomaster.models;

public class Client {
    private final int idPracownika;
    private final String imiePracownika;
    private final String nazwiskoPracownika;
    private final String emailPracownika;
    private final int wiekPracownika;
    private final int czyUprawniony;
    private final String rola;
    private final int nrTelefonu;
    private final String nazwaUzytkownika;

    public Client(int idPracownika, String imiePracownika, String nazwiskoPracownika, String emailPracownika, int wiekPracownika, int czyUprawniony, String rola, int nrTelefonu, String nazwaUzytkownika) {
        this.idPracownika = idPracownika;
        this.imiePracownika = imiePracownika;
        this.nazwiskoPracownika = nazwiskoPracownika;
        this.nrTelefonu = nrTelefonu;
        this.nazwaUzytkownika = nazwaUzytkownika;
        this.emailPracownika = emailPracownika;
        this.wiekPracownika = wiekPracownika;
        this.czyUprawniony = czyUprawniony;
        this.rola = rola;
    }

    public int getIdPracownika() {
        return idPracownika;
    }

    public String getNazwaUzytkownika() {
        return nazwaUzytkownika;
    }

    public int getNrTelefonu() {
        return nrTelefonu;
    }

    public int idPracownikaProperty() {
        return idPracownika;
    }

    public String imiePracownikaProperty() {
        return imiePracownika;
    }

    public String nazwiskoPracownikaProperty() {
        return nazwiskoPracownika;
    }

    public String emailPracownikaProperty() {
        return emailPracownika;
    }

    public int wiekPracownikaProperty() {
        return wiekPracownika;
    }

    public int czyUprawnionyProperty() {
        return czyUprawniony;
    }

    public String rolaProperty() {
        return rola;
    }

    public String getRola() {
        return rola;
    }

    public String nazwaUzytkownikaProperty() {
        return nazwaUzytkownika;
    }
}