public class Texteditor {
    private Input input;
    private Logic logic;

    public static void main(String[] args) {
        Texteditor texteditor = new Texteditor();
        texteditor.start();
    }

    public Texteditor() {
        
    }

    public void start() {
        welcome();
        logic = new Logic();
        input = new Input();
        logic.setInput(input);
        input.setLogic(logic);
        while (logic.run() == true) {
        System.out.print("Please enter your input: ");
        input.formatNextLine();

        System.out.println(input.getCommand());
        System.out.println(input.getParagraph());
        }
    }

    private void welcome() {
        System.out.println("Welcome");
    }
}