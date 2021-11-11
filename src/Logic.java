import java.util.Scanner;

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
    private Scanner scan;
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
        input.formatNextLine();
        if (input.getError() == false) {
            executeCommand();
        }
        input.setError(false);
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
            spaltenBreite = getParagraph();
            output.printOutput("toFix");
            System.out.print(getParagraph() + "\n");
        } else if (input.getCommand().equals("DEL")) {
            del();
        } else if (input.getCommand().equals("INDEX")) {
            text.indexAusgeben();
        } else if (input.getCommand().equals("REPLACE")) {
            replace();
        }
    }

    /**
     * Scan Eingabe und liefert diesen als String.
     * 
     * @return gefilterer String
     */
    private String scan() {
        scan = new Scanner(System.in);
        return input.filterParagraph(scan.nextLine());
    }

    /**
     * Unterscheided zwischen replace und replace n. Ersetzt Wort1/Absatzteil1 durch
     * Wort2/Absatzteil2.
     */
    private void replace() {
        String wort1 = "";
        String wort2 = "";
        output.printOutput("replace");
        wort1 = scan();
        output.printOutput("toReplace");
        wort2 = scan();
        if (input.getParagraph() == null) {
            text.textErsetzen(wort1, wort2);
        } else {
            text.textErsetzen(getParagraph(), wort1, wort2);
        }
    }

    /**
     * Unterscheided zwischen dummy und dummy n. Fuegt einen vorgegeben String zu
     * einer Arraylist hinzu.
     */
    private void dummy() {
        if (input.getParagraph() == null) {
            text.addDummyText();
            output.printOutput("addedDummy");
        } else {
            text.addDummyText(getParagraph());
            output.printOutput("addedDummyn");
            System.out.print(getParagraph() + "\n");
        }
    }

    /**
     * Unterscheided zwischen add und add n. Ruft zur Eingabe auf und fuegt Eingabe
     * in einer Arraylist hinzu.
     */
    private void add() {
        output.printOutput("addText");
        if (input.getParagraph() == null) {
            text.addAbsatz(scan());
            output.printOutput("addedText");
        } else {
            text.addAbsatz(scan(), getParagraph());
            output.printOutput("addedTextn");
            System.out.print(getParagraph() + "\n");
        }
    }

    /**
     * Unterscheided zwischen del und del n. Loescht Absatz.
     */
    private void del() {
        if (input.getParagraph() == null) {
            text.loescheAbsatz();
            output.printOutput("del");
        } else {
            text.loescheAbsatz(getParagraph());
            output.printOutput("deln");
            System.out.print(getParagraph() + "\n");
        }
    }

    private int getParagraph() {
        return Integer.parseInt(input.getParagraph());
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