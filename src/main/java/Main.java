import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Knihovna knihovna = new Knihovna();
    private static final String MENU_TEXT = """
                    ===== KNIHOVNA =====
                    1 - Přidat knihu
                    2 - Přidat čtenáře
                    3 - Vypůjčit knihu
                    4 - Vrátit knihu
                    5 - Audit knihovny
                    0 - Konec
                    ===================
                    Zadej volbu:""";

    public static void main(String[] args) {
        boolean bezi = true;
        while (bezi) {
            System.out.println(MENU_TEXT);
            String volba = scanner.nextLine().trim();

            switch (volba) {
                case "1" -> pridejKnihu();
                case "2" -> pridejCtenare();
                case "3" -> vypujcKnihu();
                case "4" -> vratKnihu();
                case "5" -> vypisKnihy(true);
                case "0" -> {
                    System.out.println("Konec programu");
                    bezi = false;
                }
                default -> System.out.println("Neplatná volba");
            }
        }
    }

    private static void vypisKnihy(boolean audit) {
        for (Kniha kniha : knihovna.getKnihy()) {
            System.out.print(kniha.getId().toString() + ". " + kniha.getNazev() + " - " + kniha.getAutor());
            if (audit) {
                if (kniha.jeVypujcena())
                    System.out.print(", vypůjčena čtenářem: "+ kniha.getCtenar().getJmeno());
                else
                    System.out.print(", volná");
            }
            System.out.println();
        }
    }

    private static void vypisCtenare() {
        for (Ctenar ctenar : knihovna.getCtenare()) {
            System.out.println(ctenar.getId().toString() + ". " + ctenar.getJmeno());
        }
    }

    private static void pridejKnihu() {
        System.out.println("Přidání knihy");
        System.out.println("Název knihy: ");
        String nazev = scanner.nextLine().trim();
        System.out.println("Jméno autora: ");
        String autor = scanner.nextLine().trim();

        knihovna.pridejKnihu(nazev, autor);
    }

    private static void pridejCtenare() {
        System.out.println("Přidání Čtenáře");
        System.out.println("Jméno čtenáře: ");
        String jmeno = scanner.nextLine().trim();

        knihovna.pridejCtenare(jmeno);
    }

    private static void vypujcKnihu() {
        System.out.println("Vyberte knihu, kterou chcete vypůjčit");
        vypisKnihy(false);
        Kniha kniha = vyberKnihu();

        //ošetření již vypujčené knihy
        if (kniha.getCtenar() == null) {
            System.out.println("Vyberte čtenáře, kterému chcete knihu půjčit");
            vypisCtenare();
            Ctenar ctenar = vyberCtenare();

            knihovna.vypujcKnihu(kniha.getId(), ctenar.getId());
        }
        else
            System.out.println("Kniha je již vypůjčená");
    }

    private  static  void vratKnihu() {
        System.out.println("Vyberte knihu, kterou chcete vrátit");
        vypisKnihy(false);
        Kniha kniha = vyberKnihu();

        //ošetření nevypůjčené knihy
        if (kniha.getCtenar() == null)
            System.out.println("Kniha není vypůjčená");
        else
            knihovna.vratKnihu(kniha.getId());
    }

    private static Kniha vyberKnihu() {
        Kniha kniha = null;

        System.out.println("Vyberte knihu: ");
        while (kniha == null) {
            kniha = knihovna.getKniha(scanner.nextInt());
            scanner.nextLine();
            if (kniha == null)
                System.out.println("Kniha s tímto ID neexistuje, zkuste to prosím znovu");
        }
        return kniha;
    }

    private static Ctenar vyberCtenare() {
        Ctenar ctenar = null;

        System.out.println("Vyberte čtenáře: ");
        while (ctenar == null) {
            ctenar = knihovna.getCtenar(scanner.nextInt());
            scanner.nextLine();
            if (ctenar == null)
                System.out.println("Čtenář s tímto ID neexistuje, zkuste to prosím znovu");
        }
        return ctenar;
    }
}
