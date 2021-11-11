import java.util.ArrayList;
import java.util.Scanner;

/**
 * Die Klasse enthaelt Methoden feur den Input durch die Konsole. Ueberbrueft
 * die eingaben und gibt gegebenenfalls ein Error in der Konsole aus.
 * 
 * @version 1.0
 * @author Sadikdur, Schiess
 */
public class Input {
    private Scanner scanner;
    private Text text;
    private String command;
    private String paragraph;
    private boolean error;
    private Output output;

    private static final String[] allCommands = { "ADD", "DEL", "DUMMY", "EXIT", "FORMAT RAW", "FORMAT FIX",
            "INDEX", "PRINT", "REPLACE" };

    /**
     * Konstruktor initialisiert das Output Objekt.
     * 
     * @param output Output output
     */
    public Input(Output output, Text text) {
        this.output = output;
        this.text = text;
        scanner = new Scanner(System.in);
        error = false;

    }


    /**
     * Lest den Input macht alles Grossbuchstaben und Splittet es in ein Array. Der
     * Array wird aufgeteilt in Command und Paragraph.
     * 
     * @return Input line
     */
    public void formatNextLine() {
        String formatNextLine = getInput().toUpperCase();
        String[] commandSplit = splitInput(formatNextLine);
        setCommandAndParagraph(commandSplit);
        inputCheck(text.getAbsaetze()); // TODO aufteilen in 2 methoden.
    }

    /**
     * Splitet den Input wenn eine Zahl dabei ist.
     * 
     * @param command
     * @return command und wenn eine Zahl dabei noch den Paragraph.
     */
    private String[] splitInput(String command) {
        String[] commandSplit = command.split("(?<=\\D)(?=\\d)");
        return commandSplit;
    }

    /**
     * Gibt true zurueck wenn der String command in allCommands enthalten ist.
     * 
     * @param command
     * @return boolean true/false
     */
    private boolean isCommand(String command) {
        for (int i = 0; i < allCommands.length; i++) {
            if (command.equals(allCommands[i])) {
                return true;
            }
        }
        return false;
    }

    // /**
    // * Gibt true zurueck wenn der String eine Nummer ist.
    // *
    // * @param inputSplit
    // * @return boolean true/false
    // */
    // private boolean isNumber() {
    // if (paragraph.matches("\\d+")) {
    // return true;
    // }
    // return false;
    // }

    /**
     * Wenn der Array nur aus einem Element besteht wird der Paragraph 'Null'
     * gesetzt. Wenn der Array aus zwei Elementen besteht wird der erste zum command
     * und der zweite zum Paragraphen. Wenn es mehr als zwei sind gibt es einen
     * Error aus.
     * 
     * @param commandSplit
     */
    private void setCommandAndParagraph(String[] commandSplit) {
        if (commandSplit.length == 1) {
            command = commandSplit[0].trim();
            paragraph = null;
        } else if (commandSplit.length == 2) {
            command = commandSplit[0].trim();
            paragraph = commandSplit[1];
        } else {
            System.err.println("error");
        }
    }

    /**
     * Ueberprueft ob der Input ein Command ist. Wenn ja wird ueberprueft ob der
     * Paragraph 'null' ist und wenn nicht ob er gueltig ist und ob er negativ ist.
     * 
     * @param absaetze
     */
    private void inputCheck(ArrayList<String> absaetze) {
        if (isCommand(command) == false) {
            output.printErrorOutput("noCommand");
            error = true;
        } else if (paragraph != null) {
            int index = Integer.parseInt(paragraph);
            if (command.contains("FORMAT") == false && paragraph != null
                    && (index < 0 || index > (absaetze.size()) + 1)) {
                output.printErrorOutput("notValidNumber");
                error = true;
            } else if (command.contains("FORMAT") == true && paragraph != null && index < 0) {
                output.printErrorOutput("minusNumber");
                error = true;
            }
        }
    }

    /**
     * filters all forbidden characters out. exception: 'a-z', 'A-Z', '0-9',
     * 'äöüÄÖÜ', '.,;:!?%$@&+*#(){}/\'"[]'
     * 
     * @param input the unfiltered paragraph.
     * @return String, gives the filteret paragraph back.
     */
    public String filterParagraph(String input) {
        return input.replaceAll("[^a-zA-Z0-9äöüÄÖÜ .,:;\\-!?’()\\\"%@+*[\\\\]{}\\/\\\\&#$]", "");
    }

    /**
     * @return gibt command zurueck.
     */
    public String getCommand() {
        return command;
    }

    /**
     * @return gibt Paragraph zurueck.
     */
    public String getParagraph() {
        return paragraph;
    }

    /**
     * @return gibt Input zurueck.
     */
    public String getInput() {
        return scanner.nextLine();
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
    /**
     * Beendet den Input. TODO
     */
    public void close() {
        scanner.close();
    }
}
