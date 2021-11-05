import java.util.ArrayList;

/**
 * Diese Klasse enthält methodem zur Formatierung von Absätzen.
 * 
 * @author (sadikdur)
 * @version (1)
 */
public class Format {
    private ArrayList<String> testText;
    int chars;

    public Format() {
        testText = new ArrayList<>();
        fillList();
    }

    private void fillList() {
        testText.add(0,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's "
                        + "standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type "
                        + "specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially "
                        + "unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with "
                        + "desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        testText.add(1,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry");
    }

    /**
     * Prüft ob Indexeingabe Valide ist und fügt Zeilenumbrüche nach definierter
     * maximalen Spaltenlänge.
     * 
     * @param width definiert maximale Spaltenlänge
     * @param index Position der Sammlung
     */
    public String formatFix(int width, int index) {
        String[] format = testText.get(index).split(" ");
        for (int slot = 0; slot < format.length - 1; slot++) {
            chars = chars + format[slot].length();
            if (chars > width){
                format[slot] = stringUmbruch(format[slot], width);
                if (chars + format[slot + 1].length() > width) {
                    format[slot] = format[slot] + "\n";
                }
            } else if (++chars + format[slot + 1].length() < width) {
                format[slot] = format[slot] + " ";
            } else {
                format[slot] = format[slot] + " \n";
                chars = 0;
            }
        }
        return String.join("", format);
    }

    /**
     * Prüft ob Indexeingabe Valide ist und fügt Nummer zum Absatz hinzu. Format:
     * <n> : <Absatz>
     * 
     * @param index Position der Sammlung
     */
    public String formatRaw(int index) {
        return (index + 1) + " : " + testText.get(index);
        }


    public String stringUmbruch(String format, int width) {
        String[] subString = format.split("(?<=\\G.{"+width+"})");
            for (int slot = 0; slot < subString.length; slot++) {
                if (slot == subString.length -1){
                subString[slot] = subString[slot] + " ";
                chars = subString[slot].length();
                } else {
                    subString[slot] = subString[slot] + " \n";    
                }
            }
            return String.join("", subString);
        }
    }
