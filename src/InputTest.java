
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

public class InputTest {

    @Test
    public void testFilterParagraph() {
        Input input = new Input();
        String expected = "TEST the thing";
        String text = "TE😃ST t|h|e| thin^g";
        assertEquals(expected, input.filterParagraph(text));
    }
}
