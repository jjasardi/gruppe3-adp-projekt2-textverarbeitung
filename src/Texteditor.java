public class Texteditor {
    private static Text text;
    private static Input input;
    private static Format format;

    
    public static void main(String[] args) {
        start();
        while (input.run() == false) {
        System.out.print("Please enter your input: ");
        input.formatNextLine();
        input.executeCommand();

        System.out.println(input.getCommand());
        System.out.println(input.getParagraph());
        }
    }

    public Texteditor () {
        start();
    }

    public static void start() {
        format = new Format();
        input = new Input();
        text = new Text(format);
    }
}
