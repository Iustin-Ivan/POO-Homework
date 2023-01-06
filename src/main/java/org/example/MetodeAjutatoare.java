package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

public class MetodeAjutatoare {
    public static Utilizator.Cereri tipCerere(String cerere) {
        if (cerere.equals("inlocuire buletin")) {
            return Utilizator.Cereri.inlocuire_buletin;
        } else if (cerere.equals("inregistrare venit salarial")) {
            return Utilizator.Cereri.inregistrare_venit_salarial;
        } else if (cerere.equals("inlocuire carnet de sofer")) {
            return Utilizator.Cereri.inlocuire_carnet_de_sofer;
        } else if (cerere.equals("inlocuire carnet de elev")) {
            return Utilizator.Cereri.inlocuire_carnet_de_elev;
        } else if (cerere.equals("creare act constitutiv")) {
            return Utilizator.Cereri.creare_act_constitutiv;
        } else if (cerere.equals("reinnoire autorizatie")) {
            return Utilizator.Cereri.reinnoire_autorizatie;
        }
        return Utilizator.Cereri.inregistrare_cupoane_de_pensie;
    }

    public static Date dinStringInData(String data) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date date = dateFormat.parse(data);
        return date;
    }

    public static boolean stergeToateFisiereleCuExtensia(String pathToDir, String extension) {
        boolean success = false;
        File folder = new File(pathToDir);
        File[] fList = folder.listFiles();
        for (File file : fList) {
            String pes = file.getName();
            if (pes.endsWith("." + extension)) {
                success = (new File(String.valueOf(file)).delete());
            }
        }
        return success;
    }

    public static File creeazaFisier(String nume) throws IOException {
        File fisier = new File(nume);
        return fisier;
    }


    public static void adaugaCerere(ArrayList<Utilizator> utilizatori, String[] cuvinte, File fisierIesire,
                                    Birou a, Birou b, Birou c, Birou d, Birou e) {
        String nume = cuvinte[1].substring(1);
        String cererea = cuvinte[2].substring(1);
        String tipUtilizator = "";
        for (Utilizator util : utilizatori) {
            if (util.getNume().equals(nume) || util.getReprezentant().equals(nume)) {
                tipUtilizator = util.gasesteTipUtilizator();
                try {
                    Utilizator.Cereri x = MetodeAjutatoare.tipCerere(cererea);
                    String text = util.scrieCerere(x);
                    Date dataa = MetodeAjutatoare.dinStringInData(cuvinte[3].substring(1));
                    int prioritate = Integer.valueOf(cuvinte[4].substring(1));
                    CerereCompleta cerereCompleta = new CerereCompleta(dataa, prioritate, text);
                    ArrayList<CerereCompleta> cereriUtilizatori = util.getCereriAsteptare();
                    cereriUtilizatori.add(cerereCompleta);
                    util.setCereriAsteptare(cereriUtilizatori);
                    Birou temp = MetodeAjutatoare.careBirou(a, b, c, d, e, tipUtilizator);
                    ArrayList<CerereCompleta> cereriBirou = temp.getCereriAsteptare();
                    cereriBirou.add(cerereCompleta);
                    temp.setCereriAsteptare(cereriBirou);
                    break;
                } catch (CerereInvalidaException ex) {
                    String mesajAfisat = "Utilizatorul de tip " + tipUtilizator +
                            " nu poate inainta o cerere de tip " + cererea + "\n";
                    scrieInFisier(fisierIesire, mesajAfisat);
                } catch (ParseException ex) {
                    System.out.println("Couldn't parse Date");
                }
            }
        }
    }

    public static Birou careBirou (Birou a, Birou b, Birou c, Birou d, Birou e, String numeBirou) {
        if (numeBirou.equals("angajat")) return a;
        if (numeBirou.equals("persoana")) return b;
        if (numeBirou.equals("pensionar")) return c;
        if (numeBirou.equals("entitate juridica")) return d;
        return e;
    }

    public static void afiseazaCereriAsteptare(ArrayList<Utilizator> utilizatori, String nume, File fisierIesire) {
        for(Utilizator util : utilizatori) {
            if(util.getNume().equals(nume) || util.getReprezentant().equals(nume)) {
                ArrayList<CerereCompleta> cererile;
                cererile = util.getCereriAsteptare();
                DynamicComparatorCereri comp0 = new DynamicComparatorCereri();
                Collections.sort(cererile, comp0);
                String continut;
                if (util.gasesteTipUtilizator().equals("entitate juridica")) {
                    continut = util.getReprezentant() + " - " + "cereri in asteptare:\n";
                } else {
                    continut = util.getNume() + " - " + "cereri in asteptare:\n";
                }

                for (int i = 0; i < cererile.size(); i++) {
                    continut += cererile.get(i).toString()+"\n";
                }
                scrieInFisier(fisierIesire, continut);
            }
        }
    }


    public static void adaugaUtilizator(ArrayList<Utilizator> utilizatori, String[] cuvinte) {
        String tip = cuvinte[1].substring(1);
        if (tip.equals("angajat")) {
            String nume = cuvinte[2].substring(1);
            String chestie = cuvinte[3].substring(1);
            Angajat a = new Angajat(nume, chestie);
            utilizatori.add(a);
        } else if (tip.equals("elev")) {
            String nume = cuvinte[2].substring(1);
            String chestie = cuvinte[3].substring(1);
            Elev e = new Elev(nume, chestie);
            utilizatori.add(e);
        } else if (tip.equals("pensionar")) {
            String nume = cuvinte[2].substring(1);
            Pensionar p = new Pensionar(nume);
            utilizatori.add(p);
        } else if (tip.equals("persoana")) {
            String nume = cuvinte[2].substring(1);
            Persoana p = new Persoana(nume);
            utilizatori.add(p);
        } else if (tip.equals("entitate juridica")) {
            String nume = cuvinte[3].substring(1);
            String chestie = cuvinte[2].substring(1);
            EntitateJuridica e = new EntitateJuridica(nume, chestie);
            utilizatori.add(e);
        }
    }

    public static void retrageCerere(String[] cuvinte, ArrayList<Utilizator> utilizatori, Birou a, Birou b,
                                     Birou c, Birou d, Birou e) {
        Birou temp = null;
        String nume = cuvinte[1].substring(1);
        String data = cuvinte[2].substring(1);
        try {
            Date datavar = dinStringInData(data);
            for (Utilizator util : utilizatori) {
                if (util.getNume().equals(nume) || util.getReprezentant().equals(nume)) {
                    if (util instanceof Angajat) {
                        temp = a;
                    } else if (util instanceof Persoana) {
                        temp = b;
                    } else if (util instanceof Pensionar) {
                        temp = c;
                    } else if (util instanceof EntitateJuridica) {
                        temp = d;
                    } else if (util instanceof Elev){
                        temp = e;
                    }
                    ArrayList<CerereCompleta> cererile;
                    cererile = util.getCereriAsteptare();
                    Iterator itr = cererile.iterator();
                    while (itr.hasNext()) {
                        CerereCompleta cer = (CerereCompleta) itr.next();
                        if (cer.getData().equals(datavar)) {
                            itr.remove();
                            break;
                        }
                    }
                    util.setCereriAsteptare(cererile);

                    ArrayList<CerereCompleta> cererileBirou;
                    cererileBirou = temp.getCereriAsteptare();
                    Iterator iterator = cererileBirou.iterator();
                    while (iterator.hasNext()) {
                        CerereCompleta cer = (CerereCompleta) iterator.next();
                        if (cer.getData().equals(datavar)) {
                            iterator.remove();
                            break;
                        }
                    }
                    temp.setCereriAsteptare(cererileBirou);
                    break;
                }
            }
        } catch (ParseException ex) {
            System.out.println("Couldn't parse date");
        }
    }

    public static void rezolvaCerere(String numeSolicitant, ArrayList<Utilizator> utilizatori, CerereCompleta cer) {
        for(Utilizator util : utilizatori) {
            if (util.getNume().equals(numeSolicitant) || util.getReprezentant().equals(numeSolicitant)) {
                ArrayList<CerereCompleta> cereriNerezolvate = util.getCereriAsteptare();
                ArrayList<CerereCompleta> cereriRezolvate = util.getCereriSolutionate();
                CerereCompleta j = null;
                for(CerereCompleta i : cereriNerezolvate) {
                    j = i;
                    if(i.getData().equals(cer.getData())) {
                        break;
                    }
                }
                cereriRezolvate.add(j);
                cereriNerezolvate.remove(j);
            }
        }
    }


    public static void afiseazaCereriFinalizate(String numeSolicitant, ArrayList<Utilizator> utilizatori, File fisierIesire) {
        for(Utilizator util : utilizatori) {
            if (util.getNume().equals(numeSolicitant) || util.getReprezentant().equals(numeSolicitant)) {
                ArrayList<CerereCompleta> cererile;
                cererile = util.getCereriSolutionate();
                DynamicComparatorCereri comp0 = new DynamicComparatorCereri();
                Collections.sort(cererile, comp0);
                String continut;
                if (util.gasesteTipUtilizator().equals("entitate juridica")) {
                    continut = util.getReprezentant() + " - " + "cereri in finalizate:\n";
                } else {
                    continut = util.getNume() + " - " + "cereri in finalizate:\n";
                }

                for (int i = 0; i < cererile.size(); i++) {
                    continut += cererile.get(i).toString()+"\n";
                }
                scrieInFisier(fisierIesire, continut);
            }
        }
    }


    public static void scrieInFisier(File fisierIesire, String mesaj) {
        try {
            FileWriter fw = new FileWriter(fisierIesire, true);
            fw.write(mesaj);
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
