import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Die Test-Klasse LogicTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class LogicTest
{
    private Logic logic;
    private String scannerCommand;
    private Text text;
    private Output output;
    private Input input;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Konstruktor fuer die Test-Klasse LogicTest
     */
    public LogicTest()
    {       
    }

    public void objects() {
        logic = new Logic();
        text = new Text();
        output = new Output();
        input = new Input(output, text);
    }
    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @BeforeEach
    public void setUp()
    {
        objects();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void test()
    {
        String command = "add 5";
        Scanner in = new Scanner(new ByteArrayInputStream(command.getBytes()));
        input.setCommand(in);
        logic.runNextLine();
        assertEquals("Die angegebene Zahl liegt nicht im gültigen Bereich.\n", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
    System.setOut(standardOut);
}
}



