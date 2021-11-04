import java.util.ArrayList;

public class Format {
    private ArrayList<String> testText;
    private String text;
    private String[] format4;

    public Format() {
        testText = new ArrayList<>();
        fillList();
    }

    private void fillList() {
        testText.add(0,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's"
                        + "standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type"
                        + "specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially"
                        + "unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with"
                        + "desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        testText.add(1,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry");
    }


    public void formatFix(int width, int index) {
        format4 = testText.get(index).split(" ");
        int chars = 0;
        for (int slot = 0; slot < format4.length; slot++) {
            chars = chars + format4[slot].length();
                if (chars < width) {
                    format4[slot] = format4[slot] + " ";
                } else {
                    format4[slot] = format4[slot] + "\n";
                    chars = 0;
                }
        }   
        text = index + ":" + String.join("", format4);
        System.out.println(text);
    }
}
