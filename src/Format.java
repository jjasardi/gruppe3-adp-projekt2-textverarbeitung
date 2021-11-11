import java.util.ArrayList;

/**
 * Diese Klasse enthaelt Methoden zur Formatierung von Absaetzen.
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
     * @param absaetze      Arraylist<String>
     * @param spaltenBreite maximale Zeichen pro Zeile
     */
    public void formatFix(ArrayList<String> absaetze, int spaltenBreite) {
        for (String absatz : absaetze) {
            String[] absatzStrings = absatz.split(" ");
            String spalte = "";
            for (String wort : absatzStrings) {
                int laengeWort = wort.length();
                int laengeSpalte = spalte.length();
                if (laengeWort > spaltenBreite) {
                    String[] wortTeile = wort.split("(?<=\\G.{" + spaltenBreite + "})");
                    for (String wortTeil : wortTeile) {
                        if (spalte.length() + wortTeil.length() <= spaltenBreite) {
                            spalte = wortTeil + " ";
                            System.out.print(spalte);
                        } else {
                            System.out.print("\n");
                            spalte = wortTeil + " ";
                            System.out.print(spalte);
                        }
                    }
                } else if (laengeSpalte + laengeWort > spaltenBreite) {
                    System.out.print("\n");
                    spalte = wort + " ";
                    System.out.print(spalte);
                } else if (laengeSpalte + laengeWort <= spaltenBreite) {
                    spalte += wort + " ";
                    System.out.print(wort + " ");
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
