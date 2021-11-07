import java.util.ArrayList;
import java.util.Scanner;

//import org.junit.platform.console.shadow.picocli.CommandLine.Command;

/**
 * @version 1.0
 * @author Schiess
 */
public class Input {

    private Scanner scanner;
    private Text text;
    private String[] allCommands;
    private String command;
    private String paragraph;
    private boolean exit;
    private Format format;
    private boolean error;

    /**
     * Constructor
     */
    public Input() {
        format = new Format();
        text = new Text(format);
        scanner = new Scanner(System.in);
        allCommands = new String[10];
        setAllCommands();
        error = false;
    }

    /**
     * Read Input line and test it.
     * 
     * @return Input line
     */
    void formatNextLine() {
        String formatNextLine = getInput().toUpperCase();
        String[] commandSplit = splitInput(formatNextLine);
        setCommandAndParagraph(commandSplit);
        inputCheck(text.getAbsaetze());
        if (error == false) {
            executeCommand();
        }
        error = false;
    }

    /**
     * Splits the input if a nummber is given
     */
    private String[] splitInput(String command) {
        String[] commandSplit = command.split("(?<=\\D)(?=\\d)");
        return commandSplit;
    }

    // vorbereitung
    private boolean checkSpecialCharacter() {
        // TODO when needed finish it.
        return true;
    }

    /**
     * Returns true if the String is a command from the allComand list.
     * 
     * @param command
     * @return
     */
    private boolean isCommand(String command) {
        for (int i = 0; i < allCommands.length; i++) {
            if (command.equals(allCommands[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the String is a number
     * 
     * @param inputSplit
     * @return
     */
    private boolean isNumber() {
        if (paragraph.matches("\\d+")) {
            return true;
        }
        return false;
    }

    private void setCommandAndParagraph(String[] commandSplit) {
        if (commandSplit.length == 1) {
            command = commandSplit[0];
            paragraph = null;
        } else if (commandSplit.length == 2) {
            command = commandSplit[0];
            paragraph = commandSplit[1];
        } else {
            System.err.println("error");
        }
    }

    public String getCommand() {
        return command;
    }

    public String getParagraph() {
        return paragraph;
    }

    private String getInput() {
        return scanner.nextLine();
    }

    /**
     * Sets to allCommands List the commands
     */
    private void setAllCommands() {
        allCommands[0] = "ADD";
        allCommands[1] = "DEL";
        allCommands[2] = "DUMMY";
        allCommands[3] = "EXIT";
        allCommands[4] = "FORMAT RAW";
        allCommands[5] = "FORMAT FIX";
        allCommands[7] = "INDEX";
        allCommands[8] = "PRINT";
        allCommands[9] = "REPLACE";
    }

    private void executeCommand() {
        if (command.equals("EXIT")) {
            exit();
        } else if (command.equals("ADD")) {
            if (paragraph == null) {
                String scan = "input";
                text.addAbsatz(scan);
            } else {
                // text.addAbsatz(absatz, absatzNummer););
            }
        } else if (command.equals("DUMMY")) {
            if (paragraph == null) {
                text.addDummyText();
            } else {
                text.addDummyText(Integer.valueOf(paragraph));
            }
        } else if (command.equals("PRINT")) {
            text.absaetzeAusgeben();
        } else if (command.equals("FORMAT RAW")) {
            text.setSpaltenBreite(0);
        } else if (command.equals("FORMAT FIX ")) {
            text.setSpaltenBreite(Integer.valueOf(paragraph));
        } else if (command.equals("DEL")) {
            if (paragraph == null) {
                text.loescheAbsatz();
            } else {
                text.loescheAbsatz(Integer.valueOf(paragraph));
            }
        } else if (command.equals("INDEX")) {
            text.indexAusgeben();
        } else if (command.equals("REPLACE")) {
            if (paragraph == null) {
                // text.textErsetzen(zuSuchen, ersetzenMit)
            } else {
                // text.textErsetzen(Integer.valueOf(paragraph), zuSuchen, ersetzenMit));
            }
        }

    }

    private void inputCheck(ArrayList<String> absaetze) {
        if (paragraph != null && isNumber()) {
            int index = Integer.valueOf(paragraph);
            if (command.contains("FORMAT") == false && paragraph != null && (index < 0 || index > absaetze.size())) {
                System.err.println("Der angegebe Index liegt nicht im gueltigen Indexbereich");
                error = true;
            } else if (command.contains("FORMAT") == true && paragraph != null && index < 0) {
                System.err.println("Der angegebe Zahl ist negativ");
                error = true;
            } else if (isNumber() == false) {
                System.err.println("Der eingebene Index ist keine Nummer");
                error = true;
            }
        } else if (isCommand(command) == false) {
            System.err.println("Ihre Eingabe ist keine gueltiger Befehlssatz");
            error = true;
        } else if (paragraph != null && isNumber() == false) {
            System.err.println("Der eingebene Wert ist keine Nummer");
            error = true;
        }
    }

    private void exit() {
        scanner.close();
        exit = true;
    }

    public boolean run() {
        if (exit == true) {
            return false;
        } else
            return true;
    }
}
