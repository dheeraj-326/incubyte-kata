package incubyte.util;

import incubyte.exception.InvalidAdditionInputException;
import incubyte.util.StringCalculator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class StringCalculatorTests {

    private StringCalculator stringCalculator;

    public StringCalculatorTests() {
        stringCalculator = new StringCalculator();
    }

    @Test
    void testAddTwoPositiveNumbers_MustReturnValidOutput() {
        int firstNumber = 1;
        int secondNumber = 2;
        int expectedOutput = firstNumber + secondNumber;
        String input = firstNumber + "," + secondNumber;
        int output = stringCalculator.Add(input);
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    void testAddTwoNegativeNumbers_MustReturnValidOutput() {
        int firstNumber = -1;
        int secondNumber = -2;
        int expectedOutput = firstNumber + secondNumber;
        String input = firstNumber + "," + secondNumber;
        int output = stringCalculator.Add(input);
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    void testAddNegativeAndPositiveNumbers_MustReturnValidOutput() {
        int firstNumber = 1;
        int secondNumber = -2;
        int expectedOutput = firstNumber + secondNumber;
        String input = firstNumber + "," + secondNumber;
        int output = stringCalculator.Add(input);
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    void testAddOneNumber_MustReturnValidOutput() {
        int firstNumber = 1;
        int expectedOutput = firstNumber;
        String input = firstNumber + "";
        int output = stringCalculator.Add(input);
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    void testEmptyString_MustReturnZero() {
        String input = "";
        int expectedOutput = 0;
        int output = stringCalculator.Add(input);
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    void testInvalidString_MustThrowCustomException() {
        String input = "hello,hi";
        Exception exception = Assertions.assertThrows(InvalidAdditionInputException.class, () -> {
            stringCalculator.Add(input);
        });
        Assertions.assertEquals("NaN: Invalid number", exception.getMessage());
    }

    @Test
    void testNull_MustThrowCustomException() {
        String input = null;
        Exception exception = Assertions.assertThrows(InvalidAdditionInputException.class, () -> {
            stringCalculator.Add(input);
        });
        Assertions.assertEquals("InvalidInput: null", exception.getMessage());
    }

    @Test
    void testPartiallyInvalid_MustThrowCustomException() {
        String input = "2,34P";
        Exception exception = Assertions.assertThrows(InvalidAdditionInputException.class, () -> {
            stringCalculator.Add(input);
        });
        Assertions.assertEquals("InvalidInput: Not a number", exception.getMessage());
    }
}
