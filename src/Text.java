import java.util.ArrayList;

/**
 * Diese Klasse enthaelt Methoden zur Bearbeitung und Speicherung der Absaetze
 * in einer ArrayList.
 * 
 * @author jasard
 * @version 1.0
 */

public class Text {
    private static final int NULL_KORREKTUR = 1;
    private Format format;
    private ArrayList<String> absaetze;
    Index index;
    private String dummyText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et "
            + "dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo "
            + "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
            + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    /**
     * Konstructor erstellt ein Format, Index und ArrayList Objekt.
     */
    public Text() {
        format = new Format();
        absaetze = new ArrayList<>();
        index = new Index();
    }

    public ArrayList<String> getAbsaetze() {
        return absaetze;
    }

    /**
     * fuegt den Absatz an die eingegebene Stelle hinzu
     * 
     * @param absatz       String Objekt, welches hinzugefuegt werden soll
     * @param absatzNummer An welcher Stelle der Absatz eingefuegt werden soll
     */
    public void addAbsatz(String absatz, int absatzNummer) {
        absaetze.add(absatzNummer - NULL_KORREKTUR, absatz);
    }

    /**
     * fuegt den Absatz am Ende der Arraylist hinzu
     * 
     * @param absatz String Objekt, welches hinzugefuegt werden soll
     */
    public void addAbsatz(String absatz) {
        absaetze.add(absatz);
    }

    /**
     * loescht den Absatz mit der eingegebene Absatznummer
     * 
     * @param absatzNummer Die Nummer des zu loeschenden Absatzes
     */
    public void loescheAbsatz(int absatzNummer) {
        absaetze.remove(absatzNummer - NULL_KORREKTUR);
    }

    /**
     * loescht den letzten Absatz aus der ArrayList
     */
    public void loescheAbsatz() {
        absaetze.remove(absaetze.size() - NULL_KORREKTUR);
    }

    /**
     * fuegt einen DummyText am Ende der Arraylist hinzu
     * 
     * @param absatzNummer An welcher Stelle der DummyText eingefuegt werden soll
     */
    public void addDummyText(int absatzNummer) {
        absaetze.add(absatzNummer - NULL_KORREKTUR, dummyText);
    }

    /**
     * fuegt einen DummyText an die eingegebene Stelle hinzu
     * 
     * @param absatzNummer An welcher Stelle der DummyText eingefuegt werden soll
     */
    public void addDummyText() {
        absaetze.add(dummyText);
    }

    public void absaetzeAusgeben(int spaltenBreite) {
        if (spaltenBreite == 0) {
            format.formatRaw(absaetze);
        } else
            format.formatFix(absaetze, spaltenBreite);

    }

    /**
     * Sucht nach einem Textteil in einem bestimmten Absatz und ersetzt ihn
     * 
     * @param absatzNummer in Welchem Absatz gesucht werden soll
     * @param zuSuchen     nach welchem Textteil gesucht werden soll
     * @param ersetzenMit  mit was soll das Gesuchte ersetzt werden
     */
    public void textErsetzen(int absatzNummer, String zuSuchen, String ersetzenMit) {
        String absatz = absaetze.get(absatzNummer - NULL_KORREKTUR);
        absatz = absatz.replace(zuSuchen, ersetzenMit);
        absaetze.set((absatzNummer - NULL_KORREKTUR), absatz);
    }

    /**
     * Sucht nach einem Textteil im letzten Absatz und ersetzt ihn
     * 
     * @param zuSuchen    nach welchem Textteil gesucht werden soll
     * @param ersetzenMit mit was soll das Gesuchte ersetzt werden
     */
    public void textErsetzen(String zuSuchen, String ersetzenMit) {
        String absatz = absaetze.get(absaetze.size() - NULL_KORREKTUR);
        absatz = absatz.replace(zuSuchen, ersetzenMit);
        absaetze.set(absaetze.size() - NULL_KORREKTUR, absatz);
    }


    /**
     * Aktuallisiert den Index und druckt diesen aus.
     */
    public void indexAusgeben() {
        index.indexAktualisieren(absaetze);
        index.indexAusgeben();
    }
}
