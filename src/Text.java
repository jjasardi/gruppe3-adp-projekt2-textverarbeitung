import java.util.ArrayList;
/**
 * 
 * @author jasard
 * @version 1.0
 */

public class Text {
    private Format format;
    private ArrayList<String> absaetze;
    private String dummyText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    public Text(Format format) {
        absaetze = new ArrayList<>();
        this.format = format;
    }

    
    /** 
     * fuegt den Absatz an die eingegebene Stelle hinzu
     * @param absatz String Objekt, welches hinzugefuegt werden soll
     * @param absatzNummer An welcher Stelle der Absatz eingefügt werden soll
     */
    public void addAbsatz(String absatz, int absatzNummer) {
        absaetze.add(absatzNummer, absatz);
    }

    
    /** 
     * fuegt den Absatz am Ende der Arraylist hinzu
     * @param absatz String Objekt, welches hinzugefuegt werden soll
     */
    public void addAbsatz(String absatz){
        absaetze.add(absatz);
    }

    
    /** 
     * loescht den Absatz mit der eingegebene Absatznummer
     * @param absatzNummer Die Nummer des zu löschenden Absatzes
     */
    public void loescheAbsatz(int absatzNummer){
        absaetze.remove(absatzNummer);
    }


    /** 
     * loescht den letzten Absatz aus der ArrayList
     */
    public void loescheAbsatz(){
        absaetze.remove(absaetze.size());
    }

    
    /** 
     * fuegt einen DummyText an die eingegebene Stelle hinzu
     * @param absatzNummer An welcher Stelle der DummyText eingefügt werden soll
     */
    public void addDummyText(int absatzNummer){
        absaetze.add(absatzNummer, dummyText);
    }

    public void addDummyText(){
        absaetze.add(dummyText);
    }

    public void absaetzeAusgeben(){

    }

    
    /** 
     * Sucht nach einem Textteil in einem bestimmten Absatz und ersetzt ihn
     * @param absatzNummer in Welchem Absatz gesucht werden soll
     * @param zuSuchen nach welchem Textteil gesucht werden soll
     * @param ersetzenMit mit was soll das Gesuchte ersetzt werden
     * @return String. gibt den geänderten Absatz zurück
     */
    public String textErsetzen(int absatzNummer, String zuSuchen, String ersetzenMit){
        String absatz = absaetze.get(absatzNummer);
        absatz.replace(zuSuchen, ersetzenMit);
        absaetze.set(absatzNummer, absatz);
        return absatz;
    }
}
