import java.util.ArrayList;
import java.util.Scanner;

/**
 * Diese Texteditor klasse enthält die Main methode zur Ausfuehrung des
 * Projekts.
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
     * Konstruktor initialisiert das Output Objekt.
     * 
     * @param output Output objekt
     */
    public Logic(Output output) {
        this.output = output;

    }

    /**
     * Diese methode macht das, das Input objekt auf das selbe objekt zeigt wie die
     * Input Klasse. Sie erstellt ein neues Text Objekt und initialisiert die
     * Datenfelder.
     * 
     * @param input Input objekt
     */
    public void setInput(Input input) {
        this.input = input;
        text = new Text();
        spaltenBreite = 0;
        exit = false;
    }

    public Input getInput() {
        return this.input;
    }

    /**
     * Diese methode fuehrt die methoden auf bezug zur Eingabe aus.
     */
    public void executeCommand() {
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
     * Ersetzt Wort1/Absatzteil1 durch Wort2/Absatzteil2.
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
     * Fuegt einen vorgegeben String zu einer Arraylist hinzu.
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
     * Ruft zur Eingabe auf und fuegt Eingabe in einer Arraylist hinzu.
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
     * Loescht Absatz
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

    public ArrayList<String> getAbsaetze() {
        return text.getAbsaetze();
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
}