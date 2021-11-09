import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author jasard
 * @version 1.0
 */

public class Text {
    private Format format;
    private int spaltenBreite;
    private ArrayList<String> absaetze;
    private String dummyText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et "
            + "dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo "
            + "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
            + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    private HashMap<String, Set<Integer>> index;

    public Text() {
        format = new Format();
        absaetze = new ArrayList<>();
        index = new HashMap<>();
        spaltenBreite = 0;
    }

    /**
     * fuegt den Absatz an die eingegebene Stelle hinzu
     * 
     * @param absatz       String Objekt, welches hinzugefuegt werden soll
     * @param absatzNummer An welcher Stelle der Absatz eingefügt werden soll
     */
    public void addAbsatz(String absatz, int absatzNummer) {
        absaetze.add(absatzNummer, absatz);
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
     * @param absatzNummer Die Nummer des zu löschenden Absatzes
     */
    public void loescheAbsatz(int absatzNummer) {
        absaetze.remove(absatzNummer);
    }

    /**
     * loescht den letzten Absatz aus der ArrayList
     */
    public void loescheAbsatz() {
        absaetze.remove(absaetze.size());
    }

    /**
     * fuegt einen DummyText am Ende der Arraylist hinzu
     * 
     * @param absatzNummer An welcher Stelle der DummyText eingefügt werden soll
     */
    public void addDummyText(int absatzNummer) {
        absaetze.add(absatzNummer, dummyText);
    }

    /**
     * fuegt einen DummyText an die eingegebene Stelle hinzu
     * 
     * @param absatzNummer An welcher Stelle der DummyText eingefügt werden soll
     */
    public void addDummyText() {
        absaetze.add(dummyText);
    }

    public void absaetzeAusgeben() {
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
     * @return String. gibt den geänderten Absatz zurück
     */
    public String textErsetzen(int absatzNummer, String zuSuchen, String ersetzenMit) {
        String absatz = absaetze.get(absatzNummer);
        absatz.replace(zuSuchen, ersetzenMit);
        absaetze.set(absatzNummer, absatz);
        return absatz;
    }

    /**
     * Sucht nach einem Textteil im letzten Absatz und ersetzt ihn
     * 
     * @param zuSuchen    nach welchem Textteil gesucht werden soll
     * @param ersetzenMit mit was soll das Gesuchte ersetzt werden
     * @return String. gibt den geänderten Absatz zurück
     */
    public String textErsetzen(String zuSuchen, String ersetzenMit) {
        String absatz = absaetze.get(absaetze.size());
        absatz.replace(zuSuchen, ersetzenMit);
        absaetze.set(absaetze.size(), absatz);
        return absatz;
    }

    public void setSpaltenBreite(int spaltenBreite) {
        this.spaltenBreite = spaltenBreite;
    }

    public ArrayList<String> getAbsaetze() {
        return absaetze;
    }

    public void indexAktualisieren() {
        for (int absatzNr = 1; absatzNr <= absaetze.size(); absatzNr++) {
            String absatz = absaetze.get(absatzNr - 1);
            String woerterInAbsatz[] = absatz.split(" ");
            for (String wortInAbsatz : woerterInAbsatz) {
                wortInAbsatz = wortInAbsatz.trim();
                // erste Buchstabe gross
                wortInAbsatz = wortInAbsatz.substring(0, 1).toUpperCase() + wortInAbsatz.substring(1);
                // TODO: wort = entferneSatzzeichen(wort);

                Set<Integer> vorkommenInAbsaetzeNr = new HashSet<Integer>();
                if (index.containsKey(wortInAbsatz)) {
                    vorkommenInAbsaetzeNr = index.get(wortInAbsatz);
                    vorkommenInAbsaetzeNr.add(absatzNr);
                    index.put(wortInAbsatz, vorkommenInAbsaetzeNr);
                } else {
                    vorkommenInAbsaetzeNr.add(absatzNr);
                    index.put(wortInAbsatz, vorkommenInAbsaetzeNr);
                }
            }
        }
    }

    public void indexAusgeben() {
        indexAktualisieren();
        Set<Map.Entry<String, Set<Integer>>> entrySet = index.entrySet();
        for (Map.Entry<String, Set<Integer>> entry : entrySet) {
            String wort = entry.getKey();
            Set<Integer> vorkommenInAbsaetzeNr = entry.getValue();
            if (vorkommenInAbsaetzeNr.size() > 3) {
                System.out.print(wort);
                // for (int absatzNr : vorkommenInAbsaetzeNr) {

                // }
            }
        }
    }
}
