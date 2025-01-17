package Classes;
import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    Scanner sn = new Scanner(System.in);
    private HashSet<String> isbnSet;
    private HashMap<String, Elemento> archivio;
    private List<Elemento> listaElementi= new ArrayList<>();

    public Archivio(){
        isbnSet = new HashSet<>();
        archivio = new HashMap<>();
    }

    public void aggiuntaElemento(){
        System.out.println("inserisci il codice isbn:");
        String isbn = sn.nextLine();
        if (isbnSet.contains(isbn)){
            System.out.println("Errore, nell archiovio è gia presente un elemento con questo codice isbn.");
            return;
        }
        System.out.println("Inserisci il titolo:");
        String title = sn.nextLine();

        System.out.println("Inserisci l'anno di pubblicazione:");
        int year = sn.nextInt();

        System.out.println("Inserisci il numero di pagine:");
        int pages = sn.nextInt();
        sn.nextLine();

        System.out.println("Che tipo di elemento vuoi aggiungere? (1 = Libro, 2 = Rivista)");
        int scelta = sn.nextInt();
        sn.nextLine();

        if (scelta == 1){
            System.out.println("Inserisci l'autore:");
            String autore = sn.nextLine();

            System.out.println("Inserisci il genere:");
            String genere = sn.nextLine();

            Libro nuovoLibro = new Libro(isbn, title, year, pages, autore, genere);
            isbnSet.add(isbn);
            archivio.put(isbn, nuovoLibro);
            listaElementi.add(nuovoLibro);
            System.out.println("Libro aggiunto correttamente.");

        }else if(scelta == 2){
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
            archivio.put(isbn, nuovaRivista);
            listaElementi.add(nuovaRivista);
            System.out.println("Rivista aggiunta correttamente.");

        }else {
            System.out.println("scelta non valida.");
        }
    }
    public void ricercaIsbn(){
        System.out.println("Inserisci il codice isbn da cercare nell archivio:");
        String codice = sn.nextLine();
        List<Elemento> listaIsbm = listaElementi.stream().filter(ele->ele.getIsbn().equals(codice)).toList();
        listaIsbm.forEach(ele-> System.out.println(ele));

    }
    public void eliminaIsbn(){
        System.out.println("Inserisci il codice isbn da eliminare nell archivio:");
        String codice = sn.nextLine();
        listaElementi.removeIf(ele->ele.getIsbn().equals(codice));
        System.out.println("elemento " + codice +" eliminato con successo");

    }
    public void ricercaAnno(){
        System.out.println("Inserisci l anno da cercare nell archivio:");
        int anno = sn.nextInt();
        sn.nextLine();
        List<Elemento> listaAnno = listaElementi.stream().filter(ele->ele.getYear() == anno).toList();
        listaAnno.forEach(ele-> System.out.println(ele));
    }

    public void ricercaAutore(){
        System.out.println("Inserisci il nome dell autore che vuoi cercare: ");
        String nomeAutore = sn.nextLine();
        List<Elemento> listaAutori = listaElementi.stream().filter(ele -> ele instanceof Libro && ((Libro) ele).getAutore().equals(nomeAutore)).collect(Collectors.toList());
        listaAutori.forEach(ele-> System.out.println(ele));
    }
}



