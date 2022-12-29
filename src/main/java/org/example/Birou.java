package org.example;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Birou {


    enum tipuri {
        angajati,
        pensionari,
        elevi,
        persoane,
        entitati
    }
    private ArrayList<CerereCompleta> cereriAsteptare = new ArrayList<>();

    private tipuri tipBirou;

    public Birou() {
    }

    public Birou(tipuri tipBirou) {
        this.tipBirou = tipBirou;
    }


    public ArrayList<CerereCompleta> getCereriAsteptare() {
        return cereriAsteptare;
    }

    public void setCereriAsteptare(ArrayList<CerereCompleta> cereriAsteptare) {
        this.cereriAsteptare = cereriAsteptare;
    }

    public tipuri getTipBirou() {
        return tipBirou;
    }

    public void setTipBirou(tipuri tipBirou) {
        this.tipBirou = tipBirou;
    }
    public void afiseazaCereriBirou(String numeBirou, File fisierIesire) {
        DynamicComparatorCereri d1 = new DynamicComparatorCereri();
        d1.setCompareBy(1);
        Collections.sort(this.cereriAsteptare, d1);
        String mesaj = numeBirou+ " - "+"cereri in birou:\n";
        for (int i = 0; i < this.cereriAsteptare.size(); i++) {
            mesaj += String.valueOf(this.cereriAsteptare.get(i).getPrioritate());
            mesaj += " - ";
            mesaj +=this.cereriAsteptare.get(i).toString();
            mesaj += "\n";
        }
        MetodeAjutatoare.scrieInFisier(fisierIesire, mesaj);
    }

    public CerereCompleta rezolvaCerereBirou(ArrayList<File> fisiereFunctionari, String numeFunctionar) {
        DynamicComparatorCereri d1 = new DynamicComparatorCereri();
        d1.setCompareBy(1);
        Collections.sort(this.cereriAsteptare, d1);
        CerereCompleta cerere = new CerereCompleta(null, 0, null);
        cerere.setData(this.cereriAsteptare.get(0).getData());
        cerere.setText(this.cereriAsteptare.get(0).getText());
        cerere.setPrioritate(this.cereriAsteptare.get(0).getPrioritate());
        this.cereriAsteptare.remove(0);
        String data;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        data = dateFormat.format(cerere.getData());
        String mesaj = data +" - ";
        String[] text = cerere.getText().split(" ");
        String numeleSolicitant;
        if (this.tipBirou == tipuri.entitati) {
            numeleSolicitant = text[7] + " " + text[8].substring(0, text[8].length() - 1);
        } else {
            numeleSolicitant = text[1] + " " + text[2].substring(0, text[2].length() - 1);
        }
        mesaj += numeleSolicitant;
        mesaj+= "\n";
        System.out.println(mesaj);
        for(int i = 0; i < fisiereFunctionari.size(); i++) {
            if(fisiereFunctionari.get(i).getName().contains(numeFunctionar)) {
                MetodeAjutatoare.scrieInFisier(fisiereFunctionari.get(i), mesaj);
            }
        }
        return cerere;
    }
}
