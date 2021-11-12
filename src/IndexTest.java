import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class IndexTest {
    private Text text;
    private HashMap<String, Set<Integer>> wortVerzeichnisTest;
    private HashMap<String, Integer> woerterHaeufigkeitTest;

    @Before
    public void setUp() {
        text = new Text();
        wortVerzeichnisTest = new HashMap<>();
        woerterHaeufigkeitTest = new HashMap<>();
    }

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

    @Test
    public void testNullAbsatz() {
        text.addAbsatz(null);
        text.indexAusgeben();

        assertEquals(wortVerzeichnisTest, text.index.wortVerzeichnis);
        assertEquals(woerterHaeufigkeitTest, text.index.woerterHaeufigkeit);
    }
}