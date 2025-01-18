package org.example;

import Classes.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        Archivio a1 = new Archivio();
        boolean running = true;
        System.out.println("Benvenuto, ecco una lista di comandi che puoi utilizzare: ");
        while (running) {

            System.out.println("1 - Aggiunta di un nuovo elemento nell'archivio.");
            System.out.println("2 - Ricerca nell'archivio di un elemento per codice ISBN.");
            System.out.println("3 - Rimozione di un elemento dall'archivio con codice ISBN.");
            System.out.println("4 - Ricerca nell'archivio per anno di pubblicazione.");
            System.out.println("5 - Ricerca nell'archivio per autore.");
            System.out.println("6 - Modifica di un elemento nell'archivio con codice ISBN.");
            System.out.println("7 - Statistiche dell'archivio (n totale di libri, n totale di riviste, elemento con più pagine, media pagine).");
            System.out.println("0 - Terminare l'esecuzione del programma.");
            System.out.print("Seleziona un'opzione: ");
            int scelta = sn.nextInt();
            try {

                switch (scelta) {
                    case 1:
                        a1.aggiuntaElemento();
                        break;
                    case 2:
                        try {
                            a1.ricercaIsbn();
                        }catch (IsbnNotFoundException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        a1.eliminaIsbn();
                        break;
                    case 4:
                        a1.ricercaAnno();
                        break;
                    case 5:
                        a1.ricercaAutore();
                        break;
                    case 6:

                        a1.aggiornaElemento();
                        break;
                    case 7:

                        a1.statistiche();
                        break;
                    case 0:
                        System.out.println("Chiusura del programma. Arrivederci!");
                        running = false;
                        break;
                    default:
                        System.out.println("Opzione non valida. Inserisci un numero tra 0 e 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore: inserisci solo numeri interi tra 0 e 7.");
            }
        }
    }
}
