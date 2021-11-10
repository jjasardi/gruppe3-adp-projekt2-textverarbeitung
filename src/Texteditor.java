public class Texteditor {
    private Input input;
    private Logic logic;
    private Output output;

    public static void main(String[] args) {
        Texteditor texteditor = new Texteditor();
        texteditor.start();
    }

    public Texteditor() {
        
    }

    public void start() {       
        output = new Output();
        logic = new Logic(output);
        input = new Input(output);      
        logic.setInput(input);
        input.setLogic(logic);
        System.out.print(output.getOutput("welcome"));
        while (logic.run() == true) {
        System.out.print(output.getOutput("command"));
        input.formatNextLine();

        System.out.println(input.getCommand());
        System.out.println(input.getParagraph());
        }
    }
}