import java.util.ArrayList;

/**
 * Diese Klasse enthaelt Methoden zur Formatierung von Absätzen.
 * 
 * @author (sadikdur)
 * @version (1)
 */
public class Format {
    private int zeichen;

    /**
     * Konstruktor
     */
    public Format() {
        zeichen = 0;
    }

    /**
     * Fuegt Leerzeichen und Zeilenumbrüche nach definierter maximalen Spaltenlänge
     * ein.
     * 
     * @param absatz        ArrayList
     * @param index         der Sammlung
     * @param spaltenBreite Maximale Zeichen pro Zeile
     * @return formatierter Absatz als String
     */
    public String formatFix(ArrayList<String> absatz, int index, int spaltenBreite) {
        String[] absatzString = absatz.get(index).split(" ");
        for (int slot = 0; slot < absatzString.length - 1; slot++) {
            zeichen = zeichen + absatzString[slot].length();
            if (zeichen > spaltenBreite) {
                absatzString[slot] = stringUmbruch(absatzString[slot], spaltenBreite);
                if (zeichen + absatzString[slot + 1].length() > spaltenBreite) {
                    absatzString[slot] = absatzString[slot] + "\n";
                }
            } else if (++zeichen + absatzString[slot + 1].length() < spaltenBreite) {
                absatzString[slot] = absatzString[slot] + " ";
            } else {
                absatzString[slot] = absatzString[slot] + " \n";
                zeichen = 0;
            }
        }
        stringUmbruch(absatzString[absatzString.length - 1], spaltenBreite);
        return String.join("", absatzString);
    }

    /**
     * Fuegt Indexnummer zum Absatz hinzu. Format: <n> : <Absatz>
     * 
     * @param absatz Arraylist
     * @param index  der Sammlung
     * @return formatierter Absatz als String
     */
    public String formatRaw(ArrayList<String> absatz, int index) {
        return (index + 1) + " : " + absatz.get(index);
    }

    /**
     * Bricht Strings in mehrere Teile auf, fuegt Leerzeichen oder Zeilenumbrueche
     * ein und fuegt die Teile wieder zu einem String zusammen.
     * 
     * @param absatzString  String der umgebrochen werden muss
     * @param spaltenBreite Maximale Zeichen pro Zeile
     * @return String mit Leerzeichen und Zeilenumbruechen an vorgegebener Stelle.
     */
    private String stringUmbruch(String absatzString, int spaltenBreite) {
        String[] subString = absatzString.split("(?<=\\G.{" + spaltenBreite + "})");
        for (int slot = 0; slot < subString.length; slot++) {
            if (slot == subString.length - 1) {
                subString[slot] = subString[slot] + " ";
                zeichen = subString[slot].length();
            } else {
                subString[slot] = subString[slot] + " \n";
            }
        }
        return String.join("", subString);
    }
}
