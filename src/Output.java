import java.util.HashMap;

/**
 * Diese Klasse enthaelt Methoden fuer die Ausgaben ueber System.out und System.err
 * 
 * @version 1.0
 * @author Sadikdur, Schiess
 */
public class Output {
    private HashMap<String, String> output;
    private HashMap<String, String> errorOutput;

    /**
     * Konstruktor initialisiert die Hashmaps und fuellt sie.
     */
    public Output() {
        output = new HashMap<>();
        errorOutput = new HashMap<>();
        setOutput();
        setErrorOutput();
    }

    /**
     * Diese Methode initialisiert alle Output Strings in eine HashMap.
     */
    private void setOutput() {
        output.put("welcome",
                "Willkommen zum Texteditor von ADP \n" + "Bitte geben Sie Einen Text ein oder erstellen "
                        + "sie einen Dummy-Text mit dem Befehl DUMMY. \n");
        output.put("command", "Bitte geben Sie einen Befehl ein.\n> ");
        output.put("addText", "Bitte geben Sie einen Text ein.\n> ");
        output.put("addedText", "Ihr Text wurde hinzugefügt.\n");
        output.put("addedTextn", "Ihr Text wurde zu dieser Absatznummer hinzugefügt: ");
        output.put("addedDummy", "Ein Dummy-Text wurde hinzugefügt.\n");
        output.put("addedDummyn", "Ein Dummy-Text zu dieser Absatznummer hinzugefügt: ");
        output.put("toRaw", "Das Format ist jetzt auf Standart eingestellt.\n");
        output.put("toFix", "Das Format ist jetzt auf diesen Wert eingestellt: ");
        output.put("replace", "Welches Wort oder Textteil möchten Sie ersetzen?\n> ");
        output.put("toReplace", "Mit welchem Wort oder Textteil möchten Sie Ihre Auswahl ersetzen?\n> ");
        output.put("del", "Der letzte Absatz wurde gelöscht.\n");
        output.put("deln", "Der Absatz auf dieser Absatznummer wurde gelöscht: ");
    }

    /**
     * Diese Methode initialisiert alle Error-Outputs Strings in eine HashMap.
     */
    private void setErrorOutput() {
        errorOutput.put("noCommand", "Ihre Eingabe ist keine gültiger Befehlssatz.\n");
        errorOutput.put("noNumber", "Keine Nummer angegeben.\n");
        errorOutput.put("notValidNumber", "Die angegebene Zahl liegt nicht im gültigen Bereich.\n");
        errorOutput.put("absatzLeer", "Die Absatzliste ist leer.\n");
    }

    /**
     * Druckt mit dem "Key", den richtigen String Output im System.out.
     * 
     * @param key String fuer richtige ausgabe.
     */
    public void printOutput(String key) {
        System.out.print(output.get(key));
    }

    /**
     * Druckt mit dem "Key", den richtigen String Error-Output im System.err.
     * 
     * @param key String fuer richtige ausgabe.
     */
    public void printErrorOutput(String key) {
        System.err.print(errorOutput.get(key));
    }
}
