package org.example;

import java.util.ArrayList;

public class Persoana extends Utilizator{

    private ArrayList<CerereCompleta> cereriAsteptare = new ArrayList<>();
    public ArrayList<CerereCompleta> getCereriSolutionate() {
        return cereriSolutionate;
    }

    public void setCereriSolutionate(ArrayList<CerereCompleta> cereriSolutionate) {
        this.cereriSolutionate = cereriSolutionate;
    }

    private ArrayList<CerereCompleta> cereriSolutionate = new ArrayList<>();
    private String nume;

    Persoana(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }


    @Override
    public String scrieCerere(Cereri cerere) throws CerereInvalidaException {
        if (cerere == Cereri.inlocuire_buletin || cerere == Cereri.inlocuire_carnet_de_sofer) {
            String text = "Subsemnatul " + this.getNume() + ", va rog " + "sa-mi aprobati urmatoarea solicitare:";
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
