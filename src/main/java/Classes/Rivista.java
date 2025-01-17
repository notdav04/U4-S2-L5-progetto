package Classes;

public class Rivista extends Elemento {
    public enum Periodicita{
        SETTIMANALE, MENSILE, SEMESTRALE
    }
    private Periodicita periodicita;
    public Rivista(String isbn, String title, int year, int pages, Periodicita periodicita ){
        super(isbn, title, year, pages);
        this.periodicita = periodicita;

    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    @Override
    public String toString() {
        return super.toString() + "Rivista{" +
                "periodicita=" + periodicita +
                '}';
    }
}
