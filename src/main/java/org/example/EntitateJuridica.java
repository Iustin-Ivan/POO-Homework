package org.example;

import java.util.ArrayList;

public class EntitateJuridica extends Utilizator{

    private ArrayList<CerereCompleta> cereriAsteptare = new ArrayList<>();
    public ArrayList<CerereCompleta> getCereriSolutionate() {
        return cereriSolutionate;
    }

    public void setCereriSolutionate(ArrayList<CerereCompleta> cereriSolutionate) {
        this.cereriSolutionate = cereriSolutionate;
    }

    private ArrayList<CerereCompleta> cereriSolutionate = new ArrayList<>();
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
    public String scrieCerere(Cereri cerere) throws CerereInvalidaException {
        if (cerere == Cereri.creare_act_constitutiv || cerere == Cereri.reinnoire_autorizatie) {
            String text = "Subsemnatul " + this.getNume() + ", reprezentant legal al companiei " +
                    this.getReprezentant() + ", va rog " + "sa-mi aprobati urmatoarea solicitare:";
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
