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
    private Scanner scannerCommand;
    private Scanner scannerText;
    private Text text;
    private String command;
    private Integer paragraphNr;
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
        scannerCommand = new Scanner(System.in);
        error = false;

    }

    /**
     * Lest den Input, macht alles Grossbuchstaben und Splittet es in ein Array. Der
     * Array wird aufgeteilt in Command und Paragraph zahl.
     * 
     * @return Input line
     */
    public void formatCommandNextLine() {
        String commandInput = getCommandInput().toUpperCase();
        String[] commandSplit = splitCommandInput(commandInput);
        setCommandAndParagraphNr(commandSplit);
        commandInputCheck(text.getAbsaetze());
    }

    /**
     * Splitet den Input wenn eine Zahl dabei ist.
     * 
     * @param command
     * @return command und wenn eine Zahl dabei noch den Paragraph.
     */
    private String[] splitCommandInput(String command) {
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

    private boolean checkParagraphNr(String paragraphNr) {
        if (paragraphNr.length() <= 4) {
            return true;
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
     * Wenn der Array nur aus einem Element besteht wird der Paragraph auf 0
     * gesetzt. Wenn der Array aus zwei Elementen besteht wird der erste zum command
     * und der zweite zum Paragraphen. Wenn es mehr als zwei sind gibt es einen
     * Error aus.
     * 
     * @param commandSplit
     */
    private void setCommandAndParagraphNr(String[] commandSplit) {
        if (commandSplit.length == 1) {
            command = commandSplit[0].trim();
            paragraphNr = 0;
        } else if (commandSplit.length == 2) {
            command = commandSplit[0].trim();
            if (checkParagraphNr(commandSplit[1])) {
                paragraphNr = Integer.parseInt(commandSplit[1]);
            } else {
                paragraphNr = null;
            }
        }
    }

    /**
     * Ueberprueft ob der Input ein Command ist. Wenn ja wird ueberprueft ob der
     * Paragraph 0 ist und wenn nicht ob er gueltig ist und ob er negativ ist.
     * 
     * @param absaetze
     */
    private void commandInputCheck(ArrayList<String> absaetze) {
        if (isCommand(command) == false) {
            output.printErrorOutput("noCommand");
            error = true;
        } else if (paragraphNr != 0) {
            if (command.contains("FORMAT") == false && paragraphNr != 0
                    && (paragraphNr < 1 || paragraphNr > (absaetze.size()) + 1)) {
                output.printErrorOutput("notValidNumber");
                error = true;
            } else if (command.contains("FORMAT") == true && paragraphNr != 0 && paragraphNr < 1) {
                output.printErrorOutput("minusNumber");
                error = true;
            }
        }
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
    public Integer getParagraphNr() {
        return paragraphNr;
    }

    /**
     * @return gibt Input zurueck.
     */
    public String getCommandInput() {
        return scannerCommand.nextLine();
    }

    /**
     * Scan Text Eingabe und gibt diesen als String ohne verbotene Zeichen zurueck.
     * 
     * @return gefilterer String
     */
    public String getTextInput() {
        scannerText = new Scanner(System.in);
        return filterParagraph(scannerText.nextLine());
    }

    /**
     * Filtert alle verbotenen Zeichen aus dem Absatz heraus. Ausnahme: 'a-z',
     * 'A-Z', '0-9', 'äöüÄÖÜ', '.,;:!?%$@&+*#(){}/\'"[]'
     * 
     * @param input Der ungefilterte Absatz.
     * @return Gibt den gefilterten Absatz zurueck.
     */
    private String filterParagraph(String input) {
        return input.replaceAll("[^a-zA-Z0-9äöüÄÖÜ .,:;\\-!?’()\\\"%@+*[\\\\]{}\\/\\\\&#$]", "");
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
        scannerCommand.close();
    }
}
