import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Diese Klasse enthaelt zwei Methoden zur Ausgabe der Indexfunktion.
 * 
 * @author jasard
 * @version 1.0
 */
public class Index {
    private static final int MINDESTHAEUFIGKEIT = 3;
    private static final int NULL_KORREKTUR = 1;
    private static final String SATZZEICHEN_FILTER = "[^a-zA-ZäöüÄÖÜ ]";
    HashMap<String, Set<Integer>> wortVerzeichnis;
    HashMap<String, Integer> woerterHaeufigkeit;

    /**
     * Konstruktor erstellt 2 Hashmap Objekte.
     */
    public Index() {
        wortVerzeichnis = new HashMap<>();
        woerterHaeufigkeit = new HashMap<>();
    }

    /**
     * Geht durch alle Woerter aller Absaetze durch und ergänzt das Wortverzeichnis
     * zusammen mit ihre Häufigkeit
     */
    void indexAktualisieren(ArrayList<String> absaetze) {
        if (absaetze.isEmpty() == false) {
        wortVerzeichnis.clear();
        woerterHaeufigkeit.clear();
        for (int absatzNr = 1; absatzNr <= absaetze.size(); absatzNr++) {
            String absatz = absaetze.get(absatzNr - NULL_KORREKTUR);
            absatz = absatz.replaceAll(SATZZEICHEN_FILTER, "");
            String woerterInAbsatz[] = absatz.split(" ");
            for (String wortInAbsatz : woerterInAbsatz) {
                if (!wortInAbsatz.isEmpty()) {
                    wortInAbsatz = wortInAbsatz.trim();
                    wortInAbsatz = wortInAbsatz.substring(0, 1).toUpperCase() + wortInAbsatz.substring(1);

                    Set<Integer> vorkommenInAbsaetzeNr = new HashSet<>();
                    int wortHaeufigkeit = 1;

                    if (wortVerzeichnis.containsKey(wortInAbsatz)) {
                        wortHaeufigkeit = woerterHaeufigkeit.get(wortInAbsatz) + NULL_KORREKTUR;
                        woerterHaeufigkeit.put(wortInAbsatz, wortHaeufigkeit);

                        vorkommenInAbsaetzeNr = wortVerzeichnis.get(wortInAbsatz);
                        vorkommenInAbsaetzeNr.add(absatzNr);
                        wortVerzeichnis.put(wortInAbsatz, vorkommenInAbsaetzeNr);
                    } else {
                        woerterHaeufigkeit.put(wortInAbsatz, wortHaeufigkeit);
                        vorkommenInAbsaetzeNr.add(absatzNr);
                        wortVerzeichnis.put(wortInAbsatz, vorkommenInAbsaetzeNr);
                    }
                }
            }
        }
    }
}

    /**
     * Gibt alle Woerter aus, die ueber alle Absaetze gesehen öfter als zweimal
     * vorkommen zusammen mit den Absatznummern, wo das jeweilige Wort vorkommt, als
     * Komma getrennte Zahlenfolge.
     */
    public void indexAusgeben() {
        Set<Map.Entry<String, Set<Integer>>> entrySet = wortVerzeichnis.entrySet();
        for (Map.Entry<String, Set<Integer>> entry : entrySet) {
            String wort = entry.getKey();
            Set<Integer> vorkommenInAbsaetzeNr = entry.getValue();
            int wortHaeufigkeit = woerterHaeufigkeit.get(wort);
            if (wortHaeufigkeit >= MINDESTHAEUFIGKEIT) {
                Iterator<Integer> iterate = vorkommenInAbsaetzeNr.iterator();
                System.out.print(wort);
                System.out.print(" " + iterate.next());
                while (iterate.hasNext()) {
                    System.out.print(", " + iterate.next());
                }
                System.out.println();
            }
        }
    }
}
