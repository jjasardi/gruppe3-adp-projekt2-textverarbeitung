public class Texteditor {
    private static Input input;

    
    public static void main(String[] args) {
        start();
        while (input.run() == true) {
        System.out.print("Please enter your input: ");
        input.formatNextLine();

        System.out.println(input.getCommand());
        System.out.println(input.getParagraph());
        }
    }

    public Texteditor () {
        start();
    }

    private static void start() {
        input = new Input();
    }
}