import java.util.ArrayList;
import java.util.Scanner;

/**
 * @version 1.0
 * @author Schiess
 */
public class Input {
    private Scanner scanner;
    private Logic logic;
    private String command;
    private String paragraph;
    private boolean error;
    private Output output;

    private static final String[] allCommands = { "ADD", "DEL", "DUMMY", "EXIT",
        "FORMAT RAW", "FORMAT FIX", "INDEX", "PRINT", "REPLACE" };

    public Input(Output output) {
        this.output = output;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
        scanner = new Scanner(System.in);
        error = false;
    }

    public Logic getLogic() {
        return this.logic;
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
        inputCheck(logic.getAbsaetze());
        if (error == false) {
            logic.executeCommand();
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
     * filters all forbidden characters out. exception: 'a-z', 'A-Z', '0-9',
     * 'äöüÄÖÜ', '.,;:!?%$@&+*#(){}/\'"[]'
     * 
     * @param input the unfiltered paragraph.
     * @return String, gives the filteret paragraph back.
     */
    public String filterParagraph(String input) {
        return input.replaceAll("[^a-zA-Z0-9äöüÄÖÜ .,:;\\-!?’()\\\"%@+*[\\\\]{}\\/\\\\&#$]", "");
    }

    public String getCommand() {
        return command;
    }

    public String getParagraph() {
        return paragraph;
    }

    public String getInput() {
        return scanner.nextLine();
    }

    private void inputCheck(ArrayList<String> absaetze) {
        if (isCommand(command) == false) {
            output.ErrorOutput("noCommand");
            error = true;
        } else if (paragraph != null && isNumber()) {
            int index = Integer.parseInt(paragraph);
            if (command.contains("FORMAT") == false && paragraph != null
                    && (index < 0 || index > (absaetze.size()) + 1)) {
                output.ErrorOutput("notValidNumber");
                error = true;
            } else if (command.contains("FORMAT") == true && paragraph != null && index < 0) {
                output.ErrorOutput("minusNumber");
                error = true;
            } else if (isNumber() == false) {
                output.ErrorOutput("noNumber");
                error = true;
            }
        }
    }

    public void close() {
        scanner.close();
    }
}
