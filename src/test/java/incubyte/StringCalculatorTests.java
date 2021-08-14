package incubyte;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StringCalculatorTests {

    private StringCalculator stringCalculator;

    public StringCalculatorTests() {
        stringCalculator = new StringCalculator();
    }

    @Test
    void testAddTwoPositiveNumbers() {
        int firstNumber = 1;
        int secondNumber = 2;
        int expectedOutput = firstNumber + secondNumber;
        String input = firstNumber + "," + secondNumber;
        int output = stringCalculator.Add(input);
        assertEquals(expectedOutput, output);
    }

    @Test
    void testAddTwoNegativeNumbers() {
        int firstNumber = 1;
        int secondNumber = 2;
        int expectedOutput = firstNumber + secondNumber;
        String input = firstNumber + "," + secondNumber;
        int output = stringCalculator.Add(input);
        assertEquals(expectedOutput, output);
    }
}
