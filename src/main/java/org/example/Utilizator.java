package org.example;

import java.util.ArrayList;

public abstract class Utilizator {
    enum Cereri {
        inlocuire_buletin,
        inregistrare_venit_salarial,
        inlocuire_carnet_de_sofer,
        inlocuire_carnet_de_elev,
        creare_act_constitutiv,
        reinnoire_autorizatie,
        inregistrare_cupoane_de_pensie
    }
    abstract protected String scrieCerere(Cereri cerere) throws CerereInvalidaException;
    abstract protected String getNume();

    abstract protected ArrayList<CerereCompleta> getCereriAsteptare();
    abstract protected void setCereriAsteptare(ArrayList<CerereCompleta> cereriAsteptare);
    abstract protected String getReprezentant();

    public String gasesteTipUtilizator() {
        if (this instanceof Angajat) {
            return "angajat";
        } else if (this instanceof Persoana) {
            return "persoana";
        } else if (this instanceof Pensionar) {
            return "pensionar";
        } else if (this instanceof Elev) {
            return "elev";
        }
        return "entitate juridica";
    }
}
