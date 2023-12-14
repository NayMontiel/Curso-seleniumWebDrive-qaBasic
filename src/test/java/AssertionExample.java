import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class AssertionExample {

    @Test
    @Disabled
    public void foo(){
        String actual = "pepito";
        String expected = "Pepito";
        Assertions.assertTrue(actual.contains(expected), "the name is not expected. Expected: " + expected + " Actual: " + actual );
    }
}
