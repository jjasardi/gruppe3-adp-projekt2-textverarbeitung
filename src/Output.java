import java.util.HashMap;

public class Output {

    private HashMap<String, String> output;
    private HashMap<String, String> errorOutput;

    public Output() {
        output = new HashMap<>();
        errorOutput = new HashMap<>();
        setOutput();
        setErrorOutput();
    }

    private void setOutput() {
        output.put("welcom", "Willkommen zum Texteditor von ADP /n" 
                + "Bitte geben Sie Einen Text ein oder erstellen"
                + "sie einen Dummy-Text mit dem Befehl DUMMY. /n"
                + "Fuer Hilfe geben sie HELP ein.");
        output.put("help", "HELP"); //TODO: einen Help erstellen.
        output.put("command", "Bitte geben Sie einen Befehl ein.");
        output.put("addText", "Bitte geben Sie einen Text ein.");
        output.put("addedText", "Ihr Text wurde hinzugefügt.");
        output.put("addedDummy", "Ein Dummy-Text wurde hinzugefügt.");
        output.put("toRaw", "Das Format ist jetzt auf Standart eingestellt.");
        output.put("toFix", "Das Format ist jetzt auf Ihren Wert eingestellt.");
        output.put("replace", "Welches Wort möchten Sie ersetzen?");
        output.put("toReplace", "Mit welchem Wort möchten Sie Ihre Auswahl ersetzen?");
    }

    private void setErrorOutput() {
        errorOutput.put("noCommand", "Ihre Eingabe ist keine gueltiger Befehlssatz.");
        errorOutput.put("noNumber", "Der eingebene Index ist keine Nummer.");
        errorOutput.put("minusNumber", "Die angegebene Zahl ist negativ.");
        errorOutput.put("notValidNumber", "Die angegebene Zahl liegt nicht im gueltigen Indexbereich.");
        errorOutput.put("notValidString", "Der eingegebene Text ist nicht gueltig."); // ??
        errorOutput.put("noString", "Bitte einen gueltigen Text eingeben."); // ??
        errorOutput.put("notValidWord", "Das eingegebene Wort ist nicht gueltig."); // ??
        errorOutput.put("noWord", "Bitte ein gueltiges Wort eingeben."); // ??
    }

    public String getOutput(String key) {
        return output.get(key);
    }

    public String getErrorOutput(String key) {
        return errorOutput.get(key);
    }
}
