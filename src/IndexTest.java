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
     * @Result wortVerzeichnis und woerterHaeufigkeit sind leere HashMaps.
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
     * @Result wortVerzeichnis und woerterHaeufigkeit sind leere HashMaps.
     */
    @Test
    public void testLeereArraylist() {
        text.indexAusgeben();

        assertEquals(wortVerzeichnisTest, text.index.wortVerzeichnis);
        assertEquals(woerterHaeufigkeitTest, text.index.woerterHaeufigkeit);
    }

    /**
     * Prueft ob die Index Funktion mit 3 gleichen Woerter im selben Absatz
     * funktioniert.
     * 
     * @Result das wortVerzeichnis- und woerterHaeufigkeit–Hashmaps haben folgende Werte:
     *         wortVerzeichnis = ["Lorem", <1>] ["Ipsum", <2>]
     *         woerterHaeufigkeit = ["Lorem", 3] ["Ipsum", 3]
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
     * Prueft ob die Index Funktion mit 3 gleichen Woerter in verschiedene Absaetze
     * funktioniert.
     * 
     * @Result das wortVerzeichnis- und woerterHaeufigkeit–Hashmaps haben folgende Werte:
     *         wortVerzeichnis = ["Lorem", <1, 2>] ["Ipsum", <1, 2>] 
     *         woerterHaeufigkeit = ["Lorem", 3] ["Ipsum", 3]
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
     * Prueft ob die Index Funktion mit mehr als 3 gleichen Woerter in verschiedene
     * Absaetze funktioniert.
     * 
     * @Result das wortVerzeichnis- und woerterHaeufigkeit–Hashmaps haben folgende Werte:
     *         wortVerzeichnis = ["Dein", <1, 2, 3, 4, 5, 6>] ["Keine", <1, 2, 3, 4, 5, 6>] ["Maus", <1, 2, 3, 4, 5, 6>] ["Mein", <1, 2, 3, 4, 5, 6>] 
     *         woerterHaeufigkeit = ["Dein", 6] ["Keine", 6] ["Maus", 6] ["Mein", 6]
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
     * Prueft ob die Index Funktion mit weniger 3 als gleichen Woertern
     * funktioniert.
     * 
     * @Result das wortVerzeichnis- und woerterHaeufigkeit–Hashmaps haben folgende Werte:
     *         wortVerzeichnis = ["Lorem", <1>] ["Ipsum", <2>] 
     *         woerterHaeufigkeit = ["Lorem", 2] ["Ipsum", 2]
     *         und werden nicht ausgegeben 
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
     * Prueft ob die Index Funktion mit Woerter mit Umlauten funktioniert.
     * 
     * @Result das wortVerzeichnis- und woerterHaeufigkeit–Hashmaps haben folgende Werte:
     *         wortVerzeichnis = ["Mäuse", <2>] ["Häuser", <1>]
     *         woerterHaeufigkeit = ["Maüse", 3] ["Häuser", 3]
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