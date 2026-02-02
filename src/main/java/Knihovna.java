import java.util.*;

public class Knihovna {

    private final Map<Integer, Kniha> knihy = new HashMap<>();
    private final Map<Integer, Ctenar> ctenari = new HashMap<>();
    private Integer dalsiIDKnihy = 1;
    private Integer dalsiIdCtenare = 1;

    public void pridejKnihu(String nazev, String autor) {
        Kniha kniha = new Kniha(dalsiIDKnihy++, nazev, autor);
        knihy.put(kniha.getId(), kniha);
    }

    public void pridejCtenare(String jmeno) {
        Ctenar ctenar = new Ctenar(dalsiIdCtenare++, jmeno);
        ctenari.put(ctenar.getId(), ctenar);
    }

    public void vypujcKnihu(Integer idKnihy, Integer idCtenare) {
        Kniha kniha = knihy.get(idKnihy);
        Ctenar ctenar = ctenari.get(idCtenare);

        kniha.vypujcit(ctenar);
    }

    public void vratKnihu(Integer id) {
        Kniha kniha = knihy.get(id);
        kniha.vratit();
    }

    public Collection<Kniha> getKnihy() {
        return knihy.values();
    }

    public Collection<Ctenar> getCtenare() {
        return ctenari.values();
    }

    public Kniha getKniha(Integer id) {
        return knihy.get(id);
    }

    public Ctenar getCtenar(Integer id) {
        return ctenari.get(id);
    }
}