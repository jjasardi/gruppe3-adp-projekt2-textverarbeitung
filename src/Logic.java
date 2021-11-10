import java.util.ArrayList;
import java.util.Scanner;

public class Logic {
    private Input input;
    private Text text;
    private int spaltenBreite;
    private Scanner scan;
    private boolean exit;

    public Logic () {

    }

    public void setInput(Input input) {
        this.input = input;
        text = new Text();
        spaltenBreite = 0;
        exit = false;
    }

    public void executeCommand() {
        if (input.getCommand().equals("EXIT")) {
            exit();
        } else if (input.getCommand().contains("ADD")) {
            add();
        } else if (input.getCommand().contains("DUMMY")) {
            dummy();
        } else if (input.getCommand().equals("PRINT")) {
            text.absaetzeAusgeben(spaltenBreite);
        } else if (input.getCommand().equals("FORMAT RAW")) {
            spaltenBreite = 0;
        } else if (input.getCommand().equals("FORMAT FIX ")) {// TODO check space
            spaltenBreite = getParagraph();
        } else if (input.getCommand().contains("DEL")) {
            del();
        } else if (input.getCommand().equals("INDEX")) {
            text.indexAusgeben();
        } else if (input.getCommand().contains("REPLACE")) {
            replace();
        }
    }

    private String scan() {
        scan = new Scanner(System.in);
        return scan.nextLine();
    }

    private void replace() {
        String wort1 = "";
        String wort2 = "";
        System.out.print("Wort: ");
        wort1 = scan();
        System.out.print("ersetzen durch: ");
        wort2 = scan();
        if (input.getParagraph() == null) {
            text.textErsetzen(wort1, wort2);
        } else {
            text.textErsetzen(getParagraph(), wort1, wort2);
        }
    }

    private void dummy() {
        if (input.getParagraph() == null) {
            text.addDummyText();
        } else {
            System.err.println("test");
            text.addDummyText(getParagraph());
        }
    }

    private void add() {
        System.out.print("Text: ");
        if (input.getParagraph() == null) {
            text.addAbsatz(scan());
        } else {
            text.addAbsatz(scan(), getParagraph());
        }
    }

    private void del() {
        if (input.getParagraph() == null) {
            text.loescheAbsatz();
        } else {
            text.loescheAbsatz(getParagraph());
        }
    }

    private int getParagraph() {
        return Integer.parseInt(input.getParagraph());
    }


    public ArrayList<String> getAbsaetze() {
        return text.getAbsaetze();
    }

    public boolean run() {
        if (exit == true) {
            return false;
        } else
            return true;
    }

    public void exit() {
        input.close();
        exit = true;
    }
}