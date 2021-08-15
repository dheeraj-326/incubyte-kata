package incubyte.util;

import incubyte.exception.InvalidAdditionInputException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
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
    public void testWithMultipleDelimiters_1_MustReturnSum() throws InvalidAdditionInputException {
        String input = "//\n1\n2\n3\n4";
        int output = stringCalculator.Add(input);
        Assertions.assertEquals(10, output);
    }

    @Test
    public void testWithMultipleDelimiters_2_MustReturnSum() throws InvalidAdditionInputException {
        String input = "//;1;2;3;4";
        int output = stringCalculator.Add(input);
        Assertions.assertEquals(10, output);
    }

    @Test
    public void testWithNegativeNumbers_MustThrowException() throws InvalidAdditionInputException {
        StringBuilder inputBuilder = new StringBuilder("//\n");
        for (int i = 0; i < 5; i++) {
            if (i != 0) {
                inputBuilder.append('\n');
            }
            inputBuilder.append(i);
        }
        inputBuilder.append('\n');
        inputBuilder.append("-1");

        InvalidAdditionInputException exception = Assertions.assertThrows(InvalidAdditionInputException.class, () -> {
            int output = stringCalculator.Add(inputBuilder.toString());
        });
        MatcherAssert.assertThat(exception.getMessage().contains("negatives not allowed"), new IsEqual<>(true));
    }

    @Test
    public void testWithNegativeNumber_MustThrowException() throws InvalidAdditionInputException {
        String input = "-1";

        InvalidAdditionInputException exception = Assertions.assertThrows(InvalidAdditionInputException.class, () -> {
            int output = stringCalculator.Add(input);
        });
        MatcherAssert.assertThat(exception.getMessage().contains("negatives not allowed"), new IsEqual<>(true));
    }

    @Test
    public void testWithNegativeNumber_MustThrowExceptionWithNegativeNumbers() throws InvalidAdditionInputException {
        StringBuilder inputBuilder = new StringBuilder("");
        StringBuilder exceptionMessageBuilder = new StringBuilder("negatives not allowed: ");
        boolean firstNegative = true;
        for (int i = 0; i < 5; i++) {
            int selector = (int) Math.random();
            if (i != 0)
                inputBuilder.append(',');
            int number = i;
            if (number > 0 && selector % 2 == 0) {
                number *= -1;
                if (!firstNegative)
                    exceptionMessageBuilder.append(", ");
                exceptionMessageBuilder.append(number);
                firstNegative = false;
            }
            inputBuilder.append(number);
        }
        InvalidAdditionInputException exception = Assertions.assertThrows(InvalidAdditionInputException.class, () -> {
            stringCalculator.Add(inputBuilder.toString());
        });
        Assertions.assertEquals(exceptionMessageBuilder.toString(), exception.getMessage());
    }
}
