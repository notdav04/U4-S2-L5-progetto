package org.example;
import Classes.*;
import java.util.Scanner;
public class Main
{

    public static void main( String[] args ) {
        Scanner sn = new Scanner(System.in);
        Archivio a1 = new Archivio();
        System.out.println("Benvetuto, ecco una lista di comandi che puoi utillizzare: ");
        System.out.println("1 - Aggiunta di un nuovo elemento nell archivio.");
        System.out.println("2 - Ricerca nell archivio di un elemento per codice ISBN.");
        System.out.println("3 - Rimozione di un elemento dall archivio con codice ISBN.");
        System.out.println("4 - Ricerca nell archivio per anno di pubblicazione.");
        System.out.println("5 - Ricerca nell archivio per autore.");
        System.out.println("6 - Modifica di un elemento nell archivio con codice ISBN.");
        System.out.println("7 - Statistiche dell archivio(n totale di libri, n totale di riviste, elemento con piu pagine, media pagine).");
        System.out.println("0 - Terminare l'esecuzione del programma.");

    }
}
