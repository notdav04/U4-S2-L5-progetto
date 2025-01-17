package Classes;

public class Libro extends Elemento{
    String autore;
    String genere;
    static int counter = 1;
    public Libro(String isbn, String title, int year, int pages, String autore, String genere){
        super(isbn,title, year, pages);
        this.autore = autore;
        this.genere = genere;


    }

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    @Override
    public String toString() {
        return super.toString() + "Libro{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                '}';
    }
}
