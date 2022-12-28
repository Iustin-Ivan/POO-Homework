package org.example;

import java.util.ArrayList;

public class Elev extends Utilizator{

    private ArrayList<CerereCompleta> cereriAsteptare = new ArrayList<>();
    private String nume;

    private String scoala;

    Elev(String nume, String scoala) {
        this.nume = nume;
        this.scoala = scoala;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getScoala() {
        return scoala;
    }

    public void setScoala(String scoala) {
        this.scoala = scoala;
    }

    @Override
    public String scrieCerere(Cereri cerere) {
        return null;
    }

    public ArrayList<CerereCompleta> getCereriAsteptare() {
        return cereriAsteptare;
    }

    public void setCereriAsteptare(ArrayList<CerereCompleta> cereriAsteptare) {
        this.cereriAsteptare = cereriAsteptare;
    }
}
