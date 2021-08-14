package incubyte.util;

import incubyte.exception.InvalidAdditionInputException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class StringCalculatorTests {

    private StringCalculator stringCalculator;

    public StringCalculatorTests() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void testAcceptsEmptyString_ReturnsZero() throws InvalidAdditionInputException {
        String input = "";
        int expectedOutput = 0;
        int output = stringCalculator.Add(input);
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    public void testAddTwoPositiveNumbers_MustReturnSum() throws InvalidAdditionInputException {
        int firstNumber = 1;
        int secondNumber = 2;
        int expectedOutput = firstNumber + secondNumber;
        String input = firstNumber + "," + secondNumber;
        int output = stringCalculator.Add(input);
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    public void testSingleNumbers_MustReturnSame() throws InvalidAdditionInputException {
        int firstNumber = 1;
        int expectedOutput = firstNumber;
        String input = firstNumber + "";
        int output = stringCalculator.Add(input);
        Assertions.assertEquals(expectedOutput, output);
    }
}
