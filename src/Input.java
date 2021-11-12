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

    private static final String[] allCommands = { "ADD", "DEL", "DUMMY", "EXIT", "FORMAT RAW", "FORMAT FIX", "INDEX",
            "PRINT", "REPLACE" };

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
        command = "";
        paragraphNr = null;
        String commandInput = getCommandInput().toUpperCase();
        String[] commandSplit = splitCommandInput(commandInput);
        setCommandAndParagraphNr(commandSplit);
        commandInputCheck(text.getAbsaetze());
    }

    /**
     * Splitet den Input nur zwischen Zeichen und Zahlen. z.B. add 5 = "add " + "5",
     * add
     * 
     * @param commandInput Input String
     * @return command und wenn eine Zahl dabei noch den Paragraph.
     */
    private String[] splitCommandInput(String commandInput) {
        String[] commandSplit = commandInput.split("(?<=\\D)(?=\\d)");
        return commandSplit;
    }

    /**
     * Gibt true zurueck wenn der String command in allCommands enthalten ist.
     * 
     * @param command Command String
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

    /**
     * Checkt ob der String nicht länger als 4 Zeichen ist und ob er nur Zahlen
     * enthält.
     *
     * @param paragraphNr zu testende Absatznummer
     * @return true wenn der String nur Zahlen enthält und max 4 Zeichen lang ist,
     *         somit max 999.
     */
    private boolean checkParagraphNr(String paragraphNr) {
        if (paragraphNr.length() <= 4 && paragraphNr.matches("\\d+")) {
            return true;
        }
        return false;
    }

    /**
     * Wenn der Array nur aus einem Element besteht wird der Paragraph auf "null"
     * gesetzt. Wenn der Array aus zwei Elementen besteht wird der erste zum command
     * und der zweite zum Paragraphen. Wenn es mehr als zwei sind gibt es einen
     * Error aus.
     * 
     * @param commandSplit Array das in Command und Absatznummer gesetzt wird.
     */
    private void setCommandAndParagraphNr(String[] commandSplit) {
        if (commandSplit.length == 1) {
            command = commandSplit[0].trim();
        } else if (commandSplit.length == 2) {
            command = commandSplit[0].trim();
            if (checkParagraphNr(commandSplit[1])) {
                paragraphNr = Integer.parseInt(commandSplit[1]);
            } else {
                output.printErrorOutput("notValidNumber");
                error = true;
            }
        } else {
            error = true;
        }
    }

    /**
     * 
     * 
     * @param absaetze ArrayList von Strings
     */
    private void commandInputCheck(ArrayList<String> absaetze) {
        if (isCommand(command) == false) {
            output.printErrorOutput("noCommand");
            error = true;
        } else if (paragraphNr != null) {
            if (command.equals("EXIT") || command.equals("PRINT") || command.equals("FORMAT RAW")
                    || command.equals("INDEX")) {
                output.printErrorOutput("noCommand");
                error = true;
            } else if (command.contains("DEL") == true && (paragraphNr < 1 || paragraphNr > absaetze.size())) {
                output.printErrorOutput("notValidNumber");
                error = true;
            } else if (command.contains("FORMAT") == false
                    && (paragraphNr < 1 || paragraphNr > (absaetze.size()) + 1)) {
                output.printErrorOutput("notValidNumber");
                error = true;
            } else if (command.contains("FORMAT") == true && paragraphNr < 1) {
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

    /**
     * gibt den aktuellen Error stand zurück.
     * 
     * @return Error true or false.
     */
    public boolean getError() {
        return error;
    }

    /**
     * Setzt den Error auf ture oder false.
     * 
     * @param error Aktueller Error.
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * Beendet den Input. TODO: weiss ned was genau schreiben
     */
    public void close() {
        scannerCommand.close();
    }
}
