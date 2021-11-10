import java.util.HashMap;

public class Output {
    private Logic logic;
    private Input input;

    private HashMap<String, String> output;
    private HashMap<String, String> errorOutput;

    public Output() {
        logic = new Logic(this);
        input = new Input(this);
        input.setLogic(logic);
        logic.setInput(input);
        output = new HashMap<>();
        errorOutput = new HashMap<>();
        setOutput();
        setErrorOutput();
        
    }

    private void setOutput() {
        output.put("welcome", "Willkommen zum Texteditor von ADP \n" 
                + "Bitte geben Sie Einen Text ein oder erstellen "
                + "sie einen Dummy-Text mit dem Befehl DUMMY. \n");
        output.put("command", "Bitte geben Sie einen Befehl ein.\n> ");
        output.put("addText", "Bitte geben Sie einen Text ein.\n> ");
        output.put("addedText", "Ihr Text wurde hinzugefügt.\n");
        output.put("addedTextn", "Ihr Text wurde zu diesem Index hinzugefügt: ");
        output.put("addedDummy", "Ein Dummy-Text wurde hinzugefügt.\n");
        output.put("addedDummyn", "Ein Dummy-Text zu diesem Index hinzugefügt: ");
        output.put("toRaw", "Das Format ist jetzt auf Standart eingestellt.\n");
        output.put("toFix", "Das Format ist jetzt auf diesen Wert eingestellt: ");
        output.put("replace", "Welches Wort möchten Sie ersetzen?\n> ");
        output.put("toReplace", "Mit welchem Wort möchten Sie Ihre Auswahl ersetzen?\n> ");
        output.put("del", "Der letzte Absatz wurde gelöscht.\n");
        output.put("deln", "Der Absatz aus diesem Index wurde gelöscht: ");
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

    public boolean run() {
        return logic.run();
    }

    public void formatNextLine() {
        input.formatNextLine();
    }

}
