import java.util.ArrayList;
import java.util.Scanner;

public class Logic {
    private Input input;
    private Text text;
    private Output output;
    private int spaltenBreite;
    private Scanner scan;
    private boolean exit;

    public Logic (Output output) {
        this.output = output;

    }

    public void setInput(Input input) {
        this.input = input;
        text = new Text();
        spaltenBreite = 0;
        exit = false;
    }

    public Input getInput() {
        return this.input;
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
            System.out.print(output.getOutput("toRaw"));
        } else if (input.getCommand().equals("FORMAT FIX ")) {// TODO check space
            spaltenBreite = getParagraph();
            System.out.print(output.getOutput("toFix") + getParagraph() + "\n");
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
        return input.filterParagraph(scan.nextLine());
    }

    private void replace() {
        String wort1 = "";
        String wort2 = "";
        System.out.print(output.getOutput("replace"));
        wort1 = scan();
        System.out.print(output.getOutput("toReplace"));
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
            System.out.print(output.getOutput("addedDummy"));
        } else {
            text.addDummyText(getParagraph());
            System.out.print(output.getOutput("addedDummyn") + getParagraph() + "\n");
        }
    }

    private void add() {
        System.out.print(output.getOutput("addText"));
        if (input.getParagraph() == null) {
            text.addAbsatz(scan());
            System.out.print(output.getOutput("addedText"));
        } else {
            text.addAbsatz(scan(), getParagraph());
            System.out.print(output.getOutput("addedTextn") + getParagraph() + "\n");
        }
    }

    private void del() {
        if (input.getParagraph() == null) {
            text.loescheAbsatz();
            System.out.print(output.getOutput("del"));
        } else {
            text.loescheAbsatz(getParagraph());
            System.out.print(output.getOutput("deln") + getParagraph() + "\n");
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