/**
 * Diese Texteditor klasse enthaelt die Main methode zur Ausfuehrung des
 * Projekts.
 * 
 * @author sadikdur, Schiess
 */

public class Texteditor {
    private Logic logic;

    /**
     * Main-Methode
     * 
     * @param args Eingabe vom Typ String
     */
    public static void main(String[] args) {
        Texteditor texteditor = new Texteditor();
        texteditor.start();
    }

    /**
     * leeer Konstruktor.
     */
    public Texteditor() {
    }

    /**
     * Erstellt ein neues Output Objekt und initialisiert das "Mainloop" fuer das
     * Programm.
     */
    private void start() {
        logic = new Logic();
        logic.getOutput("welcome");
        while (logic.run() == true) {
            logic.getOutput("command");
            logic.runNextLine();
        }
    }
}