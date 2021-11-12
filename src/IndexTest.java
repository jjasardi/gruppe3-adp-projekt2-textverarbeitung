import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class IndexTest {
    private Text text;
    private HashMap<String, Set<Integer>> indexTest;
    private HashMap<String, Integer> woerterHaeufigkeitTest;

    @Before
    public void setUp() {
        text = new Text();
        indexTest = new HashMap<>();
        woerterHaeufigkeitTest = new HashMap<>();
    }

    @Test
    public void testLeereAbsaetze() {
        text.addAbsatz("");
        text.addAbsatz("");
        text.addAbsatz("");
        text.addAbsatz("");
        text.indexAktualisieren();


        assertEquals(indexTest, text.index);
        assertEquals(woerterHaeufigkeitTest, text.woerterHaeufigkeit);
    }

    @Test
    public void testMindestHaeufigkeitSelbenAbsatz() {
        text.addAbsatz("lorem lorem lorem");
        text.addAbsatz("ipsum ipsum ipsum");
        text.indexAktualisieren();

        Set<Integer> vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(1);
        indexTest.put("Lorem", vorkommenInAbsaetzeNr);

        vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(2);
        indexTest.put("Ipsum", vorkommenInAbsaetzeNr);

        woerterHaeufigkeitTest.put("Lorem", 3);
        woerterHaeufigkeitTest.put("Ipsum", 3);

        assertEquals(indexTest, text.index);
        assertEquals(woerterHaeufigkeitTest, text.woerterHaeufigkeit);
    }

    @Test
    public void testMindestHaeufigkeitEintraegeVerschiedeneAbsaetze() {
        text.addAbsatz("lorem ipsum lorem ipsum");
        text.addAbsatz("lorem ipsum");
        text.indexAktualisieren();

        Set<Integer> vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(1);
        vorkommenInAbsaetzeNr.add(2);
        indexTest.put("Lorem", vorkommenInAbsaetzeNr);

        vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(1);
        vorkommenInAbsaetzeNr.add(2);
        indexTest.put("Ipsum", vorkommenInAbsaetzeNr);

        woerterHaeufigkeitTest.put("Lorem", 3);
        woerterHaeufigkeitTest.put("Ipsum", 3);

        assertEquals(indexTest, text.index);
        assertEquals(woerterHaeufigkeitTest, text.woerterHaeufigkeit);
    }

    @Test
    public void testZweiEintraege() {
        text.addAbsatz("lorem lorem");
        text.addAbsatz("ipsum ipsum");
        text.indexAktualisieren();

        Set<Integer> vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(1);
        indexTest.put("Lorem", vorkommenInAbsaetzeNr);

        vorkommenInAbsaetzeNr = new HashSet<>();
        vorkommenInAbsaetzeNr.add(2);
        indexTest.put("Ipsum", vorkommenInAbsaetzeNr);

        woerterHaeufigkeitTest.put("Lorem", 2);
        woerterHaeufigkeitTest.put("Ipsum", 2);

        assertEquals(indexTest, text.index);
        assertEquals(woerterHaeufigkeitTest, text.woerterHaeufigkeit);
    }

    @Test
    public void testNullAbsatz() {
        text.addAbsatz(null);
        text.indexAktualisieren();

        assertEquals(indexTest, text.index);
        assertEquals(woerterHaeufigkeitTest, text.woerterHaeufigkeit);
    }
}