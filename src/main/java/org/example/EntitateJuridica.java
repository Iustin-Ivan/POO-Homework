package org.example;

import java.util.ArrayList;

public class EntitateJuridica extends Utilizator{

    private ArrayList<CerereCompleta> cereriAsteptare = new ArrayList<>();
    private String nume;

    private String reprezentant;

    EntitateJuridica(String nume, String reprezentant) {
        this.nume = nume;
        this.reprezentant = reprezentant;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getReprezentant() {
        return reprezentant;
    }

    public void setReprezentant(String reprezentant) {
        this.reprezentant = reprezentant;
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
