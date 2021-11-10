public class Texteditor {
    private Output output;

    public static void main(String[] args) {
        Texteditor texteditor = new Texteditor();
        texteditor.start();
    }

    public Texteditor() {
        
    }

    public void start() {       
        output = new Output();
        System.out.print(output.getOutput("welcome"));
        while (output.run() == true) {
        System.out.print(output.getOutput("command"));
        output.formatNextLine();
        }
    }
}