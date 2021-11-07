import java.util.ArrayList;

/**
 * Diese Klasse enthaelt Methoden zur Formatierung von Absätzen.
 * 
 * @author sadikdur
 * @version 1
 */
public class Format {
    private String line;
    private ArrayList<String> testText;

    /**
     * Konstruktor
     */
    public Format() {
        line = "";
        testText = new ArrayList<>();
        fillList();

    }

    private void fillList() {
        testText.add(0,
                "STAAAAAAAAAAAAAAART Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry. ");
        testText.add(1,
                "STAAAAAAAAAAAAAAART Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry. ");
        testText.add(1,
                "STAAAAAAAAAAAAAAART Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry. ");
    }

    /**
     * Druckt Absaetze nach vorgegebener Spaltenbreite aus. Ruft dazu gegenenfalls
     * methode zum zerteilen von Strings die laenger als die Spaltenbreite sind.
     * 
     * @param absaetze      Arraylist
     * @param spaltenBreite maximale Zeichen pro Zeile
     */
    public void formatFix(ArrayList<String> absaetze, int spaltenBreite) {
        for (String absatz : absaetze) {
            String[] absatzString = absatz.split(" ");
            String spalte = "";
            String spalte2 = "";
            for (String string : absatzString) {
                if (string.length() > spaltenBreite) {
                    System.out.print("\n");
                    stringUmbruch(string, spaltenBreite);
                } else if (line.length() + spalte.length() + string.length() > spaltenBreite) {
                    System.out.print("\n");
                    spalte = string + " ";
                    System.out.print(spalte);
                } else if (line.length() + spalte.length() + string.length() <= spaltenBreite) {
                    spalte2 = string + " ";
                    spalte = spalte + spalte2;
                    System.out.print(spalte2);
                }
            }
            System.out.println(" ");
        }
    }

    /**
     * Fuegt Indexnummer zum Absatz hinzu. Format: <n> : <Absatz>
     * 
     * @param absatz Arraylist
     * @param index  der Sammlung
     */
    public void formatRaw(ArrayList<String> absaetze) {
        for (int index = 0; index < absaetze.size(); index++) {
            System.out.println((index + 1) + " : " + absaetze.get(index));
        }
    }

    /**
     * Bricht Strings auf die laenger sind als die maximale Spaltenbreite
     * @param string der zu Lange String
     * @param spaltenBreite maximale Zeichen pro Zeile
     */
    private void stringUmbruch(String string, int spaltenBreite) {
        String[] subString = string.split("(?<=\\G.{" + (spaltenBreite) + "})");
        line = "";
        for (String stringKlein : subString) {
            if (line.length() + stringKlein.length() <= spaltenBreite) {
                line = stringKlein + " ";
                System.out.print(line);
            } else {
                System.out.print("\n");
                line = stringKlein + " ";
                System.out.print(line);
            }
        }
    }
}
