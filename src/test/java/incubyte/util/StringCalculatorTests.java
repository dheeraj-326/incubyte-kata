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

    @Test
    public void testMoreThanTwoNumbers_MustReturnSum() throws InvalidAdditionInputException {
        StringBuilder inputBuilder = new StringBuilder("");
        int expectedOutput = 0;
        for (int i = 0; i < 5; i++) {
            if (i != 0)
                inputBuilder.append(',');
            inputBuilder.append(i);
            expectedOutput += i;
        }
        int output = stringCalculator.Add(inputBuilder.toString());
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    public void testWithMultipleDelimiters_MustReturnSum() throws InvalidAdditionInputException {
        StringBuilder inputBuilder = new StringBuilder("");
        int expectedOutput = 0;
        for (int i = 0; i < 5; i++) {
            if (i != 0) {
                if (((int) Math.random()) % 2 == 0)
                    inputBuilder.append(',');
                else
                    inputBuilder.append('\n');
            }
            inputBuilder.append(i);
            expectedOutput += i;
        }
        int output = stringCalculator.Add(inputBuilder.toString());
        Assertions.assertEquals(expectedOutput, output);
    }

    @Test
    public void testWithNegativeNumbers_MustThrowException() throws InvalidAdditionInputException {
        StringBuilder inputBuilder = new StringBuilder("");
        for (int i = 0; i < 5; i++) {
            if (i != 0) {
                if (((int) Math.random()) % 2 == 0)
                    inputBuilder.append(',');
                else
                    inputBuilder.append('\n');
            }
            inputBuilder.append(i);
        }
        inputBuilder.append(',');
        inputBuilder.append("-1");

        InvalidAdditionInputException exception = Assertions.assertThrows(InvalidAdditionInputException.class, () -> {
            int output = stringCalculator.Add(inputBuilder.toString());
        });
        Assertions.assertEquals("negatives not allowed", exception.getMessage());
    }

    @Test
    public void testWithNegativeNumber_MustThrowException() throws InvalidAdditionInputException {
        String input = "-1";

        InvalidAdditionInputException exception = Assertions.assertThrows(InvalidAdditionInputException.class, () -> {
            int output = stringCalculator.Add(input);
        });
        Assertions.assertEquals("negatives not allowed", exception.getMessage());
    }
}
