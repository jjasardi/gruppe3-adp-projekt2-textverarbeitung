import java.util.Scanner;

import org.junit.platform.console.shadow.picocli.CommandLine.Command;

/**
 * @version 1.0
 * @author Schiess
 */
public class Input {

    private Scanner scanner;
    private String[] allCommands;
    private String command;
    private String paragraph;

    /**
     * Constructor
     */
    public Input() {
        scanner = new Scanner(System.in);
        allCommands = new String[10];
        setAllCommands();
    }

    /**
     * Read Input line and test it.
     * 
     * @return Input line
     */
    private void formatNextLine() {
        String formatNextLine = getInput().toUpperCase();
        String[] commandSplit = splitInput(formatNextLine);
        setCommandAndParagraph(commandSplit);
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
    private boolean isNumber(String paragraph) {
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

    public static void main(String[] args) {
        // testing
        Input input = new Input();
        System.out.print("Please enter your input: ");
        input.formatNextLine();

        System.out.println(input.getCommand());
        System.out.println(input.getParagraph());
    }
}
