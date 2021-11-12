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
        if (input.getParagraphNr() == null) {
            text.textErsetzen(wort1, wort2);
        } else {
            text.textErsetzen(input.getParagraphNr(), wort1, wort2);
        }
    }

    /**
     * Unterscheided zwischen dummy und dummy n. Fuegt einen vorgegeben String zu
     * einer Arraylist hinzu.
     */
    private void dummy() {
        if (input.getParagraphNr() == null) {
            text.addDummyText();
            output.printOutput("addedDummy");
        } else {
            text.addDummyText(input.getParagraphNr());
            output.printOutput("addedDummyn");
            System.out.println(input.getParagraphNr());
        }
    }

    /**
     * Unterscheided zwischen add und add n. Ruft zur Eingabe auf und fuegt Eingabe
     * in einer Arraylist hinzu.
     */
    private void add() {
        output.printOutput("addText");
        if (input.getParagraphNr() == null) {
            text.addAbsatz(input.getTextInput());
            output.printOutput("addedText");
        } else {
            text.addAbsatz(input.getTextInput(), input.getParagraphNr());
            output.printOutput("addedTextn");
            System.out.println(input.getParagraphNr());
        }
    }

    /**
     * Unterscheided zwischen del und del n. Loescht Absatz.
     */
    private void del() {
        if (input.getParagraphNr() == null) {
            text.loescheAbsatz();
            output.printOutput("del");
        } else {
            text.loescheAbsatz(input.getParagraphNr());
            output.printOutput("deln");
            System.out.println(input.getParagraphNr());
        }
    }

    /**
     * Checkt ob Format Fix eine Nummer hat und setzt sie dann auf die spaltenBreite.
     * 
     * ParagraphNr ist hier die Spaltenbreite. TODO: k so?
     */
    private void FormatFix() {
        if (input.getParagraphNr() == null) {
            output.printErrorOutput("noNumber");
        } else {
            spaltenBreite = input.getParagraphNr();
            output.printOutput("toFix");
            System.out.println(input.getParagraphNr());
        }
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
     * Schliesst den Scan und signalisiert das Beenden des Programs.
     */
    public void exit() {
        input.close();
        exit = true;
    }

    public void getOutput(String key) {
        output.printOutput(key);
    }
    
}