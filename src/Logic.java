/**
 * Die Klasse Logic enthaelt Methoden zur Ausfuhr von Befehlen. Darueber hinaus
 * gibt es Methoden, welche die Mainloop beenden.
 * 
 * @author sadikdur, Schiess
 */

public class Logic {
    private Input input;
    private Text text;
    private Output output;
    private int spaltenBreite;
    private boolean exit;

    /**
     * Konstruktor zeigt auf das bestehende Output Objekt und erstellt 2 neue
     * Objekte. Initialisiert Werte.
     * 
     * @param output Objekt
     */
    public Logic() {
        output = new Output();
        text = new Text();
        input = new Input(output, text);
        spaltenBreite = 0;
        exit = false;
    }

    /**
     * Diese methode ruft die Eingabe aus Input auf. Falls die Eingabe gueltig ist
     * wird der entsprechende Befehl ausfgefuehrt.
     */
    public void runNextLine() {
        input.formatCommandNextLine();
        if (input.getError() == false) {
            executeCommand();
        } else {
            input.setError(false);
        }
    }

    /**
     * Diese methode fuehrt commands auf bezug zur Eingabe aus.
     */
    private void executeCommand() {
        if (input.getCommand().equals("EXIT")) {
            exit();
        } else if (input.getCommand().equals("ADD")) {
            add();
        } else if (input.getCommand().equals("DUMMY")) {
            dummy();
        } else if (input.getCommand().equals("PRINT")) {
            text.absaetzeAusgeben(spaltenBreite);
        } else if (input.getCommand().equals("FORMAT RAW")) {
            spaltenBreite = 0;
            output.printOutput("toRaw");
        } else if (input.getCommand().equals("FORMAT FIX")) {
            FormatFix();
        } else if (input.getCommand().equals("DEL")) {
            del();
        } else if (input.getCommand().equals("INDEX")) {
            text.indexAusgeben();
        } else if (input.getCommand().equals("REPLACE")) {
            replace();
        }
    }

    /**
     * Unterscheided zwischen replace und replace n. Ersetzt Wort1/Absatzteil1 durch
     * Wort2/Absatzteil2.
     */
    private void replace() {
        String wort1 = "";
        String wort2 = "";
        output.printOutput("replace");
        wort1 = input.getTextInput();
        output.printOutput("toReplace");
        wort2 = input.getTextInput();
        if (input.getCommandNr() == null) {
            text.textErsetzen(wort1, wort2);
        } else {
            text.textErsetzen(input.getCommandNr(), wort1, wort2);
        }
    }

    /**
     * Unterscheided zwischen dummy und dummy n. Fuegt einen vorgegeben String zu
     * einer Arraylist hinzu.
     */
    private void dummy() {
        if (input.getCommandNr() == null) {
            text.addDummyText();
            output.printOutput("addedDummy");
        } else {
            text.addDummyText(input.getCommandNr());
            output.printOutput("addedDummyn");
            System.out.println(input.getCommandNr());
        }
    }

    /**
     * Unterscheided zwischen add und add n. Ruft zur Eingabe auf und fuegt Eingabe
     * in einer Arraylist hinzu.
     */
    private void add() {
        output.printOutput("addText");
        if (input.getCommandNr() == null) {
            text.addAbsatz(input.getTextInput());
            output.printOutput("addedText");
        } else {
            text.addAbsatz(input.getTextInput(), input.getCommandNr());
            output.printOutput("addedTextn");
            System.out.println(input.getCommandNr());
        }
    }

    /**
     * Unterscheided zwischen del und del n. Loescht Absatz.
     */
    private void del() {
        if (input.getCommandNr() == null) {
            text.loescheAbsatz();
            output.printOutput("del");
        } else {
            text.loescheAbsatz(input.getCommandNr());
            output.printOutput("deln");
            System.out.println(input.getCommandNr());
        }
    }

    /**
     * Checkt ob Format Fix eine Nummer hat und setzt sie dann auf die
     * spaltenBreite. CommandNr ist hier die Spaltenbreite.
     */
    private void FormatFix() {
        if (input.getCommandNr() == null) {
            output.printErrorOutput("noNumber");
        } else {
            spaltenBreite = input.getCommandNr();
            output.printOutput("toFix");
            System.out.println(input.getCommandNr());
        }
    }

    /**
     * Schliesst den Scan und signalisiert das Beenden des Programs.
     */
    private void exit() {
        input.close();
        exit = true;
    }

    /**
     * Wenn das Programm beendet wird, wechselt diese Methode den Wert.
     * 
     * @return boolean true/false
     */
    public boolean run() {
        if (exit == true) {
            return false;
        } else
            return true;
    }

    /**
     * Diese erlaubt der Texteditor Klasse das Drucken von Outputs.
     * 
     * @param key Command als String
     */
    public void getOutput(String key) {
        output.printOutput(key);
    }

}