package org.example;

import java.util.ArrayList;

public class Angajat extends Utilizator{

    private ArrayList<CerereCompleta> cereriAsteptare = new ArrayList<>();
    private String nume;

    private String companie;

    Angajat(String nume, String companie) {
        this.nume = nume;
        this.companie = companie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCompanie() {
        return companie;
    }

    public void setCompanie(String companie) {
        this.companie = companie;
    }

    @Override
    public String scrieCerere(Cereri cerere) throws CerereInvalidaException{
        if (cerere == Cereri.inlocuire_buletin || cerere == Cereri.inlocuire_carnet_de_sofer
        || cerere == Cereri.inregistrare_venit_salarial) {
            String text = "Subsemnatul " + this.getNume() + ", angajat la compania " +
                    this.getCompanie() + ", va rog " + "sa-mi aprobati urmatoarea solicitare:";
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
}
