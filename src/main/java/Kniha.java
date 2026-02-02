public class Kniha {
    private final Integer id;
    private final String nazev;
    private final String autor;
    private Ctenar ctenar;

    public Kniha(Integer id, String nazev, String autor) {
        this.id = id;
        this.nazev = nazev;
        this.autor = autor;
        this.ctenar = null;
    }

    public boolean jeVypujcena() {
        return ctenar != null;
    }

    public void vypujcit(Ctenar ctenar) {
        this.ctenar = ctenar;
    }

    public void vratit() {
        this.ctenar = null;
    }

    public Integer getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public String getAutor() {
        return autor;
    }

    public Ctenar getCtenar() {
        return ctenar;
    }
}
