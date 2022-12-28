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
        try {
            MetodeAjutatoare.deleteAllFilesWithSpecificExtension("src/main/resources/output/","txt");
            File fisierIntrare = MetodeAjutatoare.createFile("src/main/resources/input/"+args[0]);
            File fisierIesire = MetodeAjutatoare.createFile("src/main/resources/output/"+args[0]);
            Scanner myReader = new Scanner(fisierIntrare);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] cuvinte = data.split(";");
                if (cuvinte[0].equals("adauga_utilizator")) {
                    MetodeAjutatoare.adaugaUtilizator(utilizatori, cuvinte);
                } else if (cuvinte[0].equals("cerere_noua")) {
                    MetodeAjutatoare.adaugaCerere(utilizatori, cuvinte, fisierIesire);
                } else if (cuvinte[0].equals("afiseaza_cereri_in_asteptare")) {
                    String nume = cuvinte[1].substring(1);
                    MetodeAjutatoare.afiseazaCereriAsteptare(utilizatori, nume, fisierIesire);
                }
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Couldn't parse Date");
        } catch (CerereInvalidaException e) {
            System.out.println("Cerere invalida");
        }
    }
}