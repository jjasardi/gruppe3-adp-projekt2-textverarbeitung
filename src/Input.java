import java.util.ArrayList;
import java.util.Scanner;

/**
 * Die Klasse enthaelt Methoden feur den Input durch die Konsole. Ueberbrueft
 * und filtert die Eingaben und gibt gegebenenfalls ein Error in der Konsole
 * aus.
 * 
 * @version 1.0
 * @author Sadikdur, Schiess
 */
public class Input {
    private Scanner scannerCommand;
    private Scanner scannerText;
    private Text text;
    private String command;
    private Integer commandNr;
    private boolean error;
    private Output output;

    private static final int NULL_KORREKTUR = 1;
    private static final String ERLAUBTE_ZEICHEN = "[^a-zA-Z0-9äöüÄÖÜ .,:;\\-!?’()\\\"%@+*[\\\\]{}\\/\\\\&#$]";
    private static final String NUR_ZAHLEN = "\\d+";
    private static final String BUCHSTABE_ZAHL_TRENNUNG = "(?<=\\D)(?=\\d)";
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
     * Initialisiert command und commandNr. Liest den Input, macht alles
     * Grossbuchstaben und Splittet die Woerter in ein Array. Das Array wird
     * aufgeteilt in Command und commandNr.
     */
    public void formatCommandNextLine() {
        command = "";
        commandNr = null;
        String commandInput = getScannerCommand().toUpperCase();
        String[] commandSplit = splitCommandInput(commandInput);
        setCommandAndcommandNr(commandSplit);
        commandInputCheck(text.getAbsaetze());
    }

    /**
     * Splitet den Input nur zwischen Zeichen und Zahlen. z.B. add 5 = "add " + "5",
     * add = "add", format fix 50 = "format fix " "50"
     * 
     * @param commandInput Input String
     * @return command und wenn eine Zahl dabei noch den Paragraph.
     */
    private String[] splitCommandInput(String commandInput) {
        String[] commandSplit = commandInput.split(BUCHSTABE_ZAHL_TRENNUNG);
        return commandSplit;
    }

    /**
     * Gibt true zurueck wenn der String command in allCommands String Array
     * enthalten ist.
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
     * enthält. Wertebereich : 0-9999
     *
     * @param commandNr zu testende Absatznummer als String
     * @return boolean true/false
     */
    private boolean checkcommandNr(String commandNr) {
        if (commandNr.length() <= 4 && commandNr.matches(NUR_ZAHLEN)) {
            return true;
        }
        return false;
    }

    /**
     * Weist Stringarray Werte zu String variabeln zu. Entsprich das Stringarray
     * nicht dem vorgegeben format, wird eine Fehlermeldung gedruckt und ein
     * Fehlersignal gesetzt.
     * 
     * @param commandSplit String Array das in Command und Absatznummer gesetzt
     *                     wird.
     */
    private void setCommandAndcommandNr(String[] commandSplit) {
        if (commandSplit.length == 1) {
            command = commandSplit[0].trim();
        } else if (commandSplit.length == 2) {
            command = commandSplit[0].trim();
            if (checkcommandNr(commandSplit[1])) {
                commandNr = Integer.parseInt(commandSplit[1]);
            } else {
                output.printErrorOutput("notValidNumber");
                error = true;
            }
        } else {
            error = true;
        }
    }

    /**
     * Ueberprueft die Eingabe ob es ein gueltiger Befehl ist. Falls eine Zahl
     * angegeben ist, wird geprueft ob die gueltig ist. Falls ein Fehler vorliegt
     * wird eine Fehlermeldung gedruckt und ein Fehlersignal gesetzt.
     * 
     * @param absaetze Arraylist der Text Klasse.
     */
    private void commandInputCheck(ArrayList<String> absaetze) {
        if (isCommand(command) == false) {
            output.printErrorOutput("noCommand");
            error = true;
        } else if (absaetze.size() == 0 && (command.equals("DEL") == true || command.equals("REPLACE") == true
                || command.equals("INDEX") == true)) {
            output.printErrorOutput("absatzLeer");
            error = true;
        } else if (commandNr != null) {
            if (command.equals("EXIT") || command.equals("PRINT") || command.equals("FORMAT RAW")
                    || command.equals("INDEX")) {
                output.printErrorOutput("noCommand");
                error = true;
            } else if (command.contains("DEL") == true
                    && (commandNr < NULL_KORREKTUR || commandNr > absaetze.size())) {
                output.printErrorOutput("notValidNumber");
                error = true;
            } else if (command.contains("FORMAT") == false
                    && (commandNr < NULL_KORREKTUR || commandNr > (absaetze.size()) + NULL_KORREKTUR)) {
                output.printErrorOutput("notValidNumber");
                error = true;
            } else if (command.contains("FORMAT") == true && commandNr < NULL_KORREKTUR) {
                output.printErrorOutput("minusNumber");
                error = true;
            }
        }
    }

    /**
     * GetCommand
     * 
     * @return Gibt command als String zurueck.
     */
    public String getCommand() {
        return command;
    }

    /**
     * GetCommandNr
     * 
     * @return Paragraph als Integer.
     */
    public Integer getCommandNr() {
        return commandNr;
    }

    /**
     * Holt den Wert aus dem ScannerCommand objekt.
     * 
     * @return ScannerCommand als String
     */
    private String getScannerCommand() {
        return scannerCommand.nextLine();
    }

    /**
     * Erstellt scannerText Scannerobjekt und gibt diesen als String ohne verbotene
     * Zeichen zurueck.
     * 
     * @return gefilterer String
     */
    public String getTextInput() {
        scannerText = new Scanner(System.in);
        return filterText(scannerText.nextLine());
    }

    /**
     * Filtert alle verbotenen Zeichen aus dem Absatz heraus. Ausnahme: 'a-z',
     * 'A-Z', '0-9', 'äöüÄÖÜ', ' .,;:!?%$@&+*#(){}/\'"[]'
     * 
     * @param input Der ungefilterte String .
     * @return Gibt den gefilterten Absatz zurueck.
     */
    private String filterText(String input) {
        return input.replaceAll(ERLAUBTE_ZEICHEN, "");
    }

    /**
     * Gibt den aktuellen error Wert zurück.
     * 
     * @return boolean true or false.
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
     * Beendet das scannerCommand und scannerText Scannerobjekt.
     */
    public void close() {
        if (scannerText != null) {
        scannerText.close();
        }
        scannerCommand.close();
    }
}
