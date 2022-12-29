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
    public String scrieCerere(Cereri cerere) throws CerereInvalidaException {
        if (cerere == Cereri.inlocuire_buletin || cerere == Cereri.inlocuire_carnet_de_elev) {
            String text = "Subsemnatul " + this.getNume() + ", elev la scoala " +
                    this.getScoala() + ", va rog " + "sa-mi aprobati urmatoarea solicitare:";
            String tip = cerere.toString();
            String[] cuvinte = tip.split("_");
            int i = 0;
            while (i < cuvinte.length) {
                text += " ";
                text += cuvinte[i];
                i++;
            }
            return text;
        } else {
            throw new CerereInvalidaException();
        }
    }

    public ArrayList<CerereCompleta> getCereriAsteptare() {
        return cereriAsteptare;
    }

    public void setCereriAsteptare(ArrayList<CerereCompleta> cereriAsteptare) {
        this.cereriAsteptare = cereriAsteptare;
    }

    public String getReprezentant() {
        return "";
    }
}
