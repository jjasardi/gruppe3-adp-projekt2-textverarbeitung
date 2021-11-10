/**
 * Diese Texteditor klasse enthält die Main methode zur Ausfuehrung des
 * Projekts.
 * 
 * @author sadikdur, Schiess
 */

public class Texteditor {
    private Output output;

    public static void main(String[] args) {
        Texteditor texteditor = new Texteditor();
        texteditor.start();
    }

    /**
     * leeer Konstruktor
     */
    public Texteditor() {

    }

    /**
     * Erstellt ein neues Output Objekt und initialisiert das "loop" fuer das
     * Programm.
     */
    private void start() {
        output = new Output();
        output.direktOutput("welcome");
        while (output.run() == true) {
            output.direktOutput("command");
            output.formatNextLine();
        }
    }
}