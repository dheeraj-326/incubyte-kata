package incubyte.util;

import incubyte.exception.InvalidAdditionInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {

    private final String defaultDelimiter = ",";
    private final String customDelimiterMarker = "//";

    private static int calledCount;

    public int GetCalledCount() {
        return StringCalculator.calledCount;
    }

    public int Add(String numbers) throws InvalidAdditionInputException {
        int sum = 0;
        StringBuilder exceptionMessageBuilder = null;
        if (numbers == null)
            throw new InvalidAdditionInputException("InvalidInput: null");
        String delimiter = getCustomDelimiter(numbers);
        numbers = removeDelimiterMetaData(numbers);
        delimiter = delimiter == null ? defaultDelimiter : delimiter;
        if (numbers.isEmpty())
            sum = 0;
        else if (!numbers.contains(delimiter)) {
            try {
                int number = Integer.parseInt(numbers);
                if (number < 0)
                    throw new InvalidAdditionInputException("negatives not allowed: " + number);
                sum = number;
            } catch (NumberFormatException ne) {
                throw new InvalidAdditionInputException("InvalidInput: Not a number");
            }
        } else {
            List<String> parts = Arrays.asList(numbers.split(delimiter));
            boolean firstNegative = true;
            try {
                for (String part : parts) {
                    int number = Integer.parseInt(part);
                    if (number < 0) {
                        if (!firstNegative)
                            exceptionMessageBuilder.append(", ");
                        else
                            exceptionMessageBuilder = new StringBuilder("negatives not allowed: ");
                        exceptionMessageBuilder.append(number);
                        firstNegative = false;
                    }
                    sum += number;
                }
            } catch (NumberFormatException ne) {
                throw new InvalidAdditionInputException("InvalidInput: Not a number");
            }
        }
        if (exceptionMessageBuilder != null)
            throw new InvalidAdditionInputException(exceptionMessageBuilder.toString());
        return sum;
    }

    private List<String> splitByDelimiter(String input) {
        String delimiter = defaultDelimiter;

        if (input.startsWith("//")) {
            delimiter = input.charAt(2) + "";
            input = input.substring(3);
        }
        return Arrays.asList(input.split(delimiter));
    }

    private String removeDelimiterMetaData(String input) {
        if (input.contains(customDelimiterMarker) && input.length() < customDelimiterMarker.length() + 1)
            return "";
        if (input.contains(customDelimiterMarker))
            return input.substring(customDelimiterMarker.length() + 1);
        return input;

    }

    private String getCustomDelimiter(String input) {
        String delimiter = null;
        if (input.length() < customDelimiterMarker.length())
            return null;
        if (input.startsWith(customDelimiterMarker)) {
            delimiter = input.charAt(2) + "";
        }
        return delimiter;
    }
}
