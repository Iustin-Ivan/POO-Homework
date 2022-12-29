package org.example;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class ManagementPrimarie {
    public static void main(String[] args) {
        ArrayList<Utilizator> utilizatori = new ArrayList<>();
        ArrayList<Functionar> functionari = new ArrayList<>();
        ArrayList<File> fisiereFunctionari = new ArrayList<>();
        try {
            MetodeAjutatoare.deleteAllFilesWithSpecificExtension("src/main/resources/output/","txt");
            File fisierIntrare = MetodeAjutatoare.createFile("src/main/resources/input/"+args[0]);
            File fisierIesire = MetodeAjutatoare.createFile("src/main/resources/output/"+args[0]);
            Scanner myReader = new Scanner(fisierIntrare);
            Birou birouAngajati = new Birou(Birou.tipuri.angajati);
            Birou birouPensionari = new Birou(Birou.tipuri.pensionari);
            Birou birouElevi = new Birou(Birou.tipuri.elevi);
            Birou birouEntitati = new Birou(Birou.tipuri.entitati);
            Birou birouPersoane = new Birou(Birou.tipuri.persoane);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] cuvinte = data.split(";");
                if (cuvinte[0].equals("adauga_utilizator")) {
                    MetodeAjutatoare.adaugaUtilizator(utilizatori, cuvinte);
                } else if (cuvinte[0].equals("cerere_noua")) {
                    MetodeAjutatoare.adaugaCerere(utilizatori, cuvinte, fisierIesire,
                            birouAngajati, birouPersoane, birouPensionari, birouEntitati, birouElevi);
                } else if (cuvinte[0].equals("afiseaza_cereri_in_asteptare")) {
                    String nume = cuvinte[1].substring(1);
                    MetodeAjutatoare.afiseazaCereriAsteptare(utilizatori, nume, fisierIesire);
                } else if (cuvinte[0].equals("retrage_cerere")) {
                    MetodeAjutatoare.retrageCerere(cuvinte, utilizatori, birouAngajati, birouPersoane,
                            birouPensionari, birouEntitati, birouElevi);
                } else if (cuvinte[0].equals("afiseaza_cereri")) {
                    String birou = cuvinte[1].substring(1);
                    Birou temp = MetodeAjutatoare.careBirou(birouAngajati, birouPersoane, birouPensionari,
                            birouEntitati, birouElevi, birou);
                    temp.afiseazaCereriBirou(birou, fisierIesire);
                } else if (cuvinte[0].equals("adauga_functionar")) {
                    String birou = cuvinte[1].substring(1);
                    Birou temp = MetodeAjutatoare.careBirou(birouAngajati, birouPersoane, birouPensionari,
                            birouEntitati, birouElevi, birou);
                    String numeFunctionar = cuvinte[2].substring(1);
                    Functionar functionar = new Functionar(temp.getTipBirou(), numeFunctionar);
                    String[] nr = args[0].split("_");
                    File fisierFunctionar = MetodeAjutatoare.createFile("src/main/resources/output/"
                    +"functionar_"+numeFunctionar+".txt");
                    functionari.add(functionar);
                    fisiereFunctionari.add(fisierFunctionar);
                } else if (cuvinte[0].equals("rezolva_cerere")) {
                    String tipBirou = cuvinte[1].substring(1);
                    String numeFunctionar = cuvinte[2].substring(1);
                    Birou temp = MetodeAjutatoare.careBirou(birouAngajati, birouPersoane, birouPensionari,
                            birouEntitati, birouElevi, tipBirou);
                    String numeSolicitant = "";
                    CerereCompleta cerereDeRezolvat = temp.rezolvaCerereBirou(fisiereFunctionari, numeFunctionar);
                    String[] text = cerereDeRezolvat.getText().split(" ");
                    numeSolicitant = text[1]+" "+text[2].substring(0, text[2].length()-1);
                    MetodeAjutatoare.rezolvaCerere(numeSolicitant, utilizatori, cerereDeRezolvat);
                } else if (cuvinte[0].equals("afiseaza_cereri_finalizate")) {
                    String nume = cuvinte[1].substring(1);
                    MetodeAjutatoare.afiseazaCereriFinalizate(nume, utilizatori, fisierIesire);
                }
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}