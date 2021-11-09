import java.util.ArrayList;

public class Logic {
    private Input input;
    private Text text;

    public Logic() {
        input = new Input();
        text = new Text();
    }

    public void executeCommand() {
        if (input.getCommand().equals("EXIT")) {
            input.exit();
        } else if (input.getCommand().equals("ADD")) {
            if (input.getParagraph() == null) {
                String scan = "input";
                text.addAbsatz(scan);
            } else {
                // text.addAbsatz(absatz, absatzNummer););
            }
        } else if (input.getCommand().equals("DUMMY")) {
            if (input.getParagraph() == null) {
                text.addDummyText();
            } else {
                text.addDummyText(getParagraph());
            }
        } else if (input.getCommand().equals("PRINT")) {
            text.absaetzeAusgeben();
        } else if (input.getCommand().equals("FORMAT RAW")) {
            text.setSpaltenBreite(0);
        } else if (input.getCommand().equals("FORMAT FIX ")) {// TODO check space
            text.setSpaltenBreite(getParagraph());
        } else if (input.getCommand().equals("DEL")) {
            if (input.getParagraph() == null) {
                text.loescheAbsatz();
            } else {
                text.loescheAbsatz(getParagraph());
            }
        } else if (input.getCommand().equals("INDEX")) {
            text.indexAusgeben();
        } else if (input.getCommand().equals("REPLACE")) {
            if (input.getParagraph() == null) {
                // text.textErsetzen(zuSuchen, ersetzenMit)
            } else {
                // text.textErsetzen(Integer.parseInt(paragraph), zuSuchen, ersetzenMit));
            }
        }
    }
    
    private int getParagraph() {
        return Integer.parseInt(input.getParagraph());
    }

    public ArrayList<String> getAbsaetze() {
        return text.getAbsaetze();
    } 

}