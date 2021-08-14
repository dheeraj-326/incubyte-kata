package incubyte.util;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class StringCalculatorTests {

    private StringCalculator stringCalculator;

    public StringCalculatorTests() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void testAcceptsEmptyString_ReturnsZero() {
        String input = "";
        int expectedOutput = 0;
        int output = stringCalculator.Add(input);
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    public void testAddTwoPositiveNumbers_MustReturnSum() {
        int firstNumber = 1;
        int secondNumber = 2;
        int expectedOutput = firstNumber + secondNumber;
        String input = firstNumber + "," + secondNumber;
        int output = stringCalculator.Add(input);
        Assertions.assertEquals(expectedOutput, output);
    }
}
