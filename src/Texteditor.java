public class Texteditor {
    private static Input input;
    private static Logic logic;

    
    public static void main(String[] args) {
        start();
        while (logic.run() == true) {
        System.out.print("Please enter your input: ");
        input.formatNextLine();

        System.out.println(input.getCommand());
        System.out.println(input.getParagraph());
        }
    }

    public Texteditor () {
        start();
    }

    public static void start() {
        welcome();
        logic = new Logic();
        input = new Input();
        logic.setInput(input);
        input.setLogic(logic);
    }

    private static void welcome() {
        System.out.println("Welcome");
    }
}