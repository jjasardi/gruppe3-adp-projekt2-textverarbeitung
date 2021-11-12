import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse enthaelt TestMethoden für die Index Funktion.
 * 
 * @author jasard
 * @version 1.0
 */

public class IndexTest {
    private Text text;
    private HashMap<String, Set<Integer>> wortVerzeichnisTest;
    private HashMap<String, Integer> woerterHaeufigkeitTest;

    /**
     * Erstellt 2 Hashmap und ein Text Objekt.
     */
    @Before
    public void setUp() {
        text = new Text();
        wortVerzeichnisTest = new HashMap<>();
        woerterHaeufigkeitTest = new HashMap<>();
    }

    /**
     * Prueft ob die Index Funktion mit leeren Absaetzen funktioniert.
     * 
     * @Result Wortverzeichniss und wortVerzeichnis sind leer.
     */
    @Test
    public void testLeereAbsaetze() {
        text.addAbsatz("");
        text.addAbsatz("");
        text.addAbsatz("");
        text.addAbsatz("");
        text.indexAusgeben();


        assertEquals(wortVerzeichnisTest, text.index.wortVerzeichnis);
        assertEquals(woerterHaeufigkeitTest, text.index.woerterHaeufigkeit);
    }

    /**
     * Prueft ob die Index Funktion mit leerer Arraylist funktioniert.
     * 
     * @Result Wortverzeichniss und wortVerzeichnis sind leer.
     */
    @Test
    public void testLeereArraylist() {
        text.indexAusgeben();


        assertEquals(wortVerzeichnisTest, text.index.wortVerzeichnis);
        assertEquals(woerterHaeufigkeitTest, text.index.woerterHaeufigkeit);
    }

    /**
     * Prueft wie die Index Funktion mit 3 gleichen Woerter im selben Absatz funktioniert.
     * 
     * @Result Es wird nur eine Zahl pro Wort ausgegeben.
     */
    @Test
    public void testMindestHaeufigkeitSelbenAbsatz() {
        text.addAbsatz("lorem lorem lorem");
        text.addAbsatz("ipsum ipsum ipsum");
        text.indexAusgeben();

        Set<Integer> vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(1);
        wortVerzeichnisTest.put("Lorem", vorkommenInAbsaetzeNr);

        vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(2);
        wortVerzeichnisTest.put("Ipsum", vorkommenInAbsaetzeNr);

        woerterHaeufigkeitTest.put("Lorem", 3);
        woerterHaeufigkeitTest.put("Ipsum", 3);

        assertEquals(wortVerzeichnisTest, text.index.wortVerzeichnis);
        assertEquals(woerterHaeufigkeitTest, text.index.woerterHaeufigkeit);
    }

    /**
     * Prueft wie die Index Funktion mit 3 gleichen Woerter in verschiedene Absaetze funktioniert.
     * 
     * @Result Es werden mehrere Woerter ausgegeben in folgender Form: Wort n, n...
     */
    @Test
    public void testMindestHaeufigkeitEintraegeVerschiedeneAbsaetze() {
        text.addAbsatz("lorem ipsum lorem ipsum");
        text.addAbsatz("lorem ipsum");
        text.indexAusgeben();

        Set<Integer> vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(1);
        vorkommenInAbsaetzeNr.add(2);
        wortVerzeichnisTest.put("Lorem", vorkommenInAbsaetzeNr);

        vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(1);
        vorkommenInAbsaetzeNr.add(2);
        wortVerzeichnisTest.put("Ipsum", vorkommenInAbsaetzeNr);

        woerterHaeufigkeitTest.put("Lorem", 3);
        woerterHaeufigkeitTest.put("Ipsum", 3);

        assertEquals(wortVerzeichnisTest, text.index.wortVerzeichnis);
        assertEquals(woerterHaeufigkeitTest, text.index.woerterHaeufigkeit);
    }

        /**
     * Prueft wie die Index Funktion mit mehr als 3 gleichen Woerter in verschiedene Absaetze funktioniert.
     * 
     * @Result Es werden mehrere Woerter ausgegeben in folgender Form: Wort n, n...
     */
    @Test
    public void testMindestHaeufigkeitEintraegeVerschiedeneAbsaetzeMehrAlsDrei() {
        text.addAbsatz("Mein dein keine Maus");
        text.addAbsatz("Mein dein keine Maus");
        text.addAbsatz("Mein dein keine Maus");
        text.addAbsatz("Mein dein keine Maus");
        text.addAbsatz("Mein dein keine Maus");
        text.addAbsatz("Mein dein keine Maus");
        text.indexAusgeben();

        Set<Integer> vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(1);
        vorkommenInAbsaetzeNr.add(2);
        vorkommenInAbsaetzeNr.add(3);
        vorkommenInAbsaetzeNr.add(4);
        vorkommenInAbsaetzeNr.add(5);
        vorkommenInAbsaetzeNr.add(6);
        wortVerzeichnisTest.put("Mein", vorkommenInAbsaetzeNr);

        vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(1);
        vorkommenInAbsaetzeNr.add(2);
        vorkommenInAbsaetzeNr.add(3);
        vorkommenInAbsaetzeNr.add(4);
        vorkommenInAbsaetzeNr.add(5);
        vorkommenInAbsaetzeNr.add(6);
        wortVerzeichnisTest.put("Dein", vorkommenInAbsaetzeNr);

        vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(1);
        vorkommenInAbsaetzeNr.add(2);
        vorkommenInAbsaetzeNr.add(3);
        vorkommenInAbsaetzeNr.add(4);
        vorkommenInAbsaetzeNr.add(5);
        vorkommenInAbsaetzeNr.add(6);
        wortVerzeichnisTest.put("Keine", vorkommenInAbsaetzeNr);

        vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(1);
        vorkommenInAbsaetzeNr.add(2);
        vorkommenInAbsaetzeNr.add(3);
        vorkommenInAbsaetzeNr.add(4);
        vorkommenInAbsaetzeNr.add(5);
        vorkommenInAbsaetzeNr.add(6);
        wortVerzeichnisTest.put("Maus", vorkommenInAbsaetzeNr);

        woerterHaeufigkeitTest.put("Mein", 6);
        woerterHaeufigkeitTest.put("Dein", 6);
        woerterHaeufigkeitTest.put("Keine", 6);
        woerterHaeufigkeitTest.put("Maus", 6);

        assertEquals(wortVerzeichnisTest, text.index.wortVerzeichnis);
        assertEquals(woerterHaeufigkeitTest, text.index.woerterHaeufigkeit);
    }

    /**
     * Prueft wie die Index Funktion mit weniger 3 als gleichen Woertern funktioniert.
     * 
     * @Result keine Ausgabe
     */
    @Test
    public void testZweiEintraege() {
        text.addAbsatz("lorem lorem");
        text.addAbsatz("ipsum ipsum");
        text.indexAusgeben();

        Set<Integer> vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(1);
        wortVerzeichnisTest.put("Lorem", vorkommenInAbsaetzeNr);

        vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(2);
        wortVerzeichnisTest.put("Ipsum", vorkommenInAbsaetzeNr);

        woerterHaeufigkeitTest.put("Lorem", 2);
        woerterHaeufigkeitTest.put("Ipsum", 2);

        assertEquals(wortVerzeichnisTest, text.index.wortVerzeichnis);
        assertEquals(woerterHaeufigkeitTest, text.index.woerterHaeufigkeit);
    }

    /**
     * Prueft wie die Index Funktion mit Umlauten funktioniert.
     * 
     * @Result Umlaute werden wie normale Worter behandelt.
     */
    @Test
    public void testUmlaute() {
        text.addAbsatz("Häuser Häuser Häuser");
        text.addAbsatz("Mäuse Mäuse Mäuse");
        text.indexAusgeben();

        Set<Integer> vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(1);
        wortVerzeichnisTest.put("Häuser", vorkommenInAbsaetzeNr);

        vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(2);
        wortVerzeichnisTest.put("Mäuse", vorkommenInAbsaetzeNr);

        woerterHaeufigkeitTest.put("Häuser", 3);
        woerterHaeufigkeitTest.put("Mäuse", 3);

        assertEquals(wortVerzeichnisTest, text.index.wortVerzeichnis);
        assertEquals(woerterHaeufigkeitTest, text.index.woerterHaeufigkeit);
    }
}