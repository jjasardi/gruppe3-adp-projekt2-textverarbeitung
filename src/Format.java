import java.util.ArrayList;

/**
 * Diese Klasse enthaelt Methoden zur Formatierung von Absätzen.
 * 
 * @author sadikdur
 * @version 1
 */
public class Format {

    /**
     * leerer Konstruktor
     */
    public Format() {
    }

    /**
     * Druckt Absaetze nach vorgegebener Spaltenbreite aus. Wenn Woerter laenger
     * sind als die Spaltenbreite werden sie auseinandergebrochen.
     * 
     * @param absaetze      Arraylist
     * @param spaltenBreite maximale Zeichen pro Zeile
     */
    public void formatFix(ArrayList<String> absaetze, int spaltenBreite) {
        for (String absatz : absaetze) {
            String[] absatzString = absatz.split(" ");
            String spalte = "";
            for (String string : absatzString) {
                if (string.length() > spaltenBreite) {
                    String[] subString = string.split("(?<=\\G.{" + spaltenBreite + "})");
                    for (String stringKlein : subString) {
                        if (spalte.length() + stringKlein.length() <= spaltenBreite) {
                            spalte = stringKlein + " ";
                            System.out.print(spalte);
                        } else {
                            System.out.print("\n");
                            spalte = stringKlein + " ";
                            System.out.print(spalte);
                        }
                    }
                } else if (spalte.length() + string.length() > spaltenBreite) {
                    System.out.print("\n");
                    spalte = string + " ";
                    System.out.print(spalte);
                } else if (spalte.length() + string.length() <= spaltenBreite) {
                    spalte = spalte + string + " ";
                    System.out.print(string + " ");
                }
            }
            System.out.println("\n");
        }
    }

    /**
     * Fuegt Indexnummer zum Absatz hinzu und druckt diesen. Format: <n> : <Absatz>
     * 
     * @param absaetze Arraylist
     */
    public void formatRaw(ArrayList<String> absaetze) {
        for (int index = 0; index < absaetze.size(); index++) {
            System.out.println((index + 1) + " : " + absaetze.get(index));
        }
    }
}
