package Classes;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Archivio {
    Scanner sn = new Scanner(System.in);
    File file = new File("txt/info.txt");
    private HashSet<String> isbnSet;

    private List<Elemento> listaElementi = new ArrayList<>();


    Libro l1 = new Libro("128-129", "libro 1", 2004, 390, "io", "fantasy");
    Libro l2 = new Libro("129-130", "libro 2", 2006, 400, "io", "romantico");
    Libro l3 = new Libro("130-131", "libro 3", 2008, 350, "io", "comico");
    Rivista r1 = new Rivista("131-132", "rivista 1", 2010, 250, Rivista.Periodicita.SETTIMANALE);
    Rivista r2 = new Rivista("132-133", "rivista 2", 2012, 250, Rivista.Periodicita.SEMESTRALE);


    public Archivio() {
        isbnSet = new HashSet<>();
        listaElementi.add(l1);
        listaElementi.add(l2);
        listaElementi.add(l3);
        listaElementi.add(r1);
        listaElementi.add(r2);


    }

    public void aggiuntaElemento() {
        System.out.println("inserisci il codice isbn:");
        String isbn = sn.nextLine();
        if (isbnSet.contains(isbn)) {
            System.out.println("Errore, nell archivio è gia presente un elemento con questo codice isbn.");
            return;
        }
        System.out.println("Inserisci il titolo:");
        String title = sn.nextLine();

        System.out.println("Inserisci l'anno di pubblicazione:");
        int year = sn.nextInt();
        sn.nextLine();

        System.out.println("Inserisci il numero di pagine:");
        int pages = sn.nextInt();
        sn.nextLine();

        System.out.println("Che tipo di elemento vuoi aggiungere? (1 = Libro, 2 = Rivista)");
        int scelta = sn.nextInt();
        sn.nextLine();

        if (scelta == 1) {
            System.out.println("Inserisci l'autore:");
            String autore = sn.nextLine();

            System.out.println("Inserisci il genere:");
            String genere = sn.nextLine();

            Libro nuovoLibro = new Libro(isbn, title, year, pages, autore, genere);
            isbnSet.add(isbn);

            listaElementi.add(nuovoLibro);
            try {
                FileUtils.writeStringToFile(file, "aggiunto elemento " + nuovoLibro.getIsbn() + "\n", true);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Libro aggiunto correttamente.");

        } else if (scelta == 2) {
            System.out.println("Inserisci la periodicità (SETTIMANALE, MENSILE, SEMESTRALE):");
            String periodicitaInput = sn.nextLine().toUpperCase();

            Rivista.Periodicita periodicita;
            try {
                periodicita = Rivista.Periodicita.valueOf(periodicitaInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: periodicità non valida.");
                return;
            }

            Rivista nuovaRivista = new Rivista(isbn, title, year, pages, periodicita);
            isbnSet.add(isbn);

            listaElementi.add(nuovaRivista);
            try {
                FileUtils.writeStringToFile(file, "aggiunto elemento " + nuovaRivista.getIsbn() + "\n", true);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Rivista aggiunta correttamente.");

        } else {
            System.out.println("scelta non valida.");
        }
    }

    public void ricercaIsbn() throws IsbnNotFoundException {
        System.out.println("Inserisci il codice isbn da cercare nell archivio:");
        String codice = sn.nextLine();

        Elemento eleIsbn = listaElementi.stream().filter(ele -> ele.getIsbn().equals(codice)).findFirst().get();
        if (listaElementi.contains(eleIsbn)) {
            System.out.println(eleIsbn);
            try {
                FileUtils.writeStringToFile(file, "cercato elemento " + eleIsbn.getIsbn() + "\n", true);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new IsbnNotFoundException("Errore: isbn non trovato nell archivio!");
        }


    }

    public void eliminaIsbn() {
        System.out.println("Inserisci il codice isbn da eliminare nell archivio:");
        String codice = sn.nextLine();
        listaElementi.removeIf(ele -> ele.getIsbn().equals(codice));
        try {
            FileUtils.writeStringToFile(file, "eliminato elemento " + codice + "\n", true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("elemento " + codice + " eliminato con successo");

    }

    public void ricercaAnno() {
        System.out.println("Inserisci l anno da cercare nell archivio:");
        int anno = sn.nextInt();
        sn.nextLine();
        List<Elemento> listaAnno = listaElementi.stream().filter(ele -> ele.getYear() == anno).toList();
        listaAnno.forEach(ele -> System.out.println(ele));
        try {
            FileUtils.writeStringToFile(file, "ricerca anno " + anno + "\n", true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void ricercaAutore() {
        System.out.println("Inserisci il nome dell autore che vuoi cercare: ");
        String nomeAutore = sn.nextLine();
        List<Elemento> listaAutori = listaElementi.stream().filter(ele -> ele instanceof Libro && ((Libro) ele).getAutore().equals(nomeAutore)).collect(Collectors.toList());
        listaAutori.forEach(ele -> System.out.println(ele));
        try {
            FileUtils.writeStringToFile(file, "ricerca autore " + nomeAutore + "\n", true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void aggiornaElemento() {
        System.out.println("inserisci il codice ISBN dell elemento da modificare: ");
        String codice = sn.nextLine();
        Elemento elemento = listaElementi.stream().filter(ele -> ele.getIsbn().equals(codice)).findFirst().get();
        System.out.println("risultati------------------------------------------");
        System.out.println(elemento);
        System.out.println("inserisci il nuovo titolo: ");
        String title = sn.nextLine();
        System.out.println("inserisci il nuovo anno di pubblicazione: ");
        int year = sn.nextInt();
        sn.nextLine();
        System.out.println("inserisci il nuovo numero di pagine: ");
        int pages = sn.nextInt();
        sn.nextLine();
        if (elemento instanceof Libro) {
            System.out.println("inserisci il nuovo autore: ");
            String autore = sn.nextLine();
            System.out.println("inserisci il nuovo genere: ");
            String genere = sn.nextLine();
            elemento.setTitle(title);
            elemento.setYear(year);
            elemento.setPages(pages);
            ((Libro) elemento).setAutore(autore);
            ((Libro) elemento).setGenere(genere);
            System.out.println("modifica effettuata correttamente: " + elemento);
        } else if (elemento instanceof Rivista) {
            System.out.println("inserisci la nuova periodicita(settimanale, mensile, semestrale):");
            String periodicita = sn.nextLine().toUpperCase();
            Rivista.Periodicita nuovaPeriodicita;
            try {
                nuovaPeriodicita = Rivista.Periodicita.valueOf(periodicita);
            } catch (IllegalArgumentException e) {
                System.out.println("errore: valore non accettato");
                return;
            }
            elemento.setTitle(title);
            elemento.setYear(year);
            elemento.setPages(pages);
            ((Rivista) elemento).setPeriodicita(nuovaPeriodicita);
            System.out.println("modifica effettuata correttamente: " + elemento);
        }
        try {
            FileUtils.writeStringToFile(file, "modifica elemento " + codice + "\n", true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void statistiche() {
        Long totaleLibri = listaElementi.stream().filter(ele -> ele instanceof Libro).count();
        Long totaleRiviste = listaElementi.stream().filter(ele -> ele instanceof Rivista).count();
        Elemento pagesMax = listaElementi.stream().max(Comparator.comparing(Elemento::getPages)).get();
        Double avgPages = listaElementi.stream().mapToDouble(Elemento::getPages).average().getAsDouble();

        System.out.println("il totale dei libri è: " + totaleLibri);
        System.out.println("il totale delle riviste è: " + totaleRiviste);
        System.out.println("l elemento con piu pagine è: " + pagesMax);
        System.out.println("il numero medio di pagine è: " + avgPages);
        try {
            FileUtils.writeStringToFile(file, "stampa statistiche \n", true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


}



