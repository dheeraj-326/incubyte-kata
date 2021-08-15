package incubyte.util;

import incubyte.exception.InvalidAdditionInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {

    private final String defaultDelimiter = ",";
    private final String customDelimiterMarker = "//";
    private final String multiDelimiterBegin = "[";
    private final String multiDelimiterEnd = "]";

    private static int calledCount;

    public int GetCalledCount() {
        return StringCalculator.calledCount;
    }

    public int Add(String input) throws InvalidAdditionInputException {
        StringCalculator.calledCount++;
        int sum = 0;
        StringBuilder exceptionMessageBuilder = null;
        if (input == null)
            throw new InvalidAdditionInputException("InvalidInput: null");
        String[] inputParts = splitDelimiterAndInput(input);
        String delimiter = parseCustomDelimiter(inputParts[0]);
        String numbers = inputParts[1];
        if (numbers.isEmpty())
            sum = 0;
        else if (!numbers.contains(delimiter)) {
            try {
                int number = Integer.parseInt(numbers);
                if (number < 0)
                    throw new InvalidAdditionInputException("negatives not allowed: " + number);
                sum = number > 1000 ? 0 : number;
            } catch (NumberFormatException ne) {
                throw new InvalidAdditionInputException("InvalidInput: Not a number");
            }
        } else {
            List<String> parts = Arrays.asList(numbers.split(delimiter));
            boolean firstNegative = true;
            try {
                for (String part : parts) {
                    if (part.equals(""))
                        continue;
                    int number = Integer.parseInt(part);
                    if (number > 1000)
                        continue;
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

    private String removeDelimiterMetaData(String input, String delimiter) {
        if (input.contains(customDelimiterMarker) && input.length() < customDelimiterMarker.length() + 1)
            return "";
        if (input.contains(customDelimiterMarker))
            return input.substring(customDelimiterMarker.length() + 1);
        return input;

    }

    private String[] splitDelimiterAndInput(String input) {
        if (input.length() < customDelimiterMarker.length() || !input.startsWith(customDelimiterMarker)) {
            return new String[] { null, input };
        }
        String delimiterPart = null;
        String inputPart = null;
        int delimiterBeginIndex = input.indexOf(multiDelimiterBegin);
        int delimiterEndIndex = input.indexOf(multiDelimiterEnd);
        if (delimiterBeginIndex != -1 && delimiterEndIndex != -1) {
            delimiterPart = input.substring(0, delimiterEndIndex + 1);
            inputPart = delimiterEndIndex == input.length() - 1 ? "" : input.substring(delimiterEndIndex + 1);
            return new String[] { delimiterPart, inputPart };
        } else {
            return new String[] { input.substring(0, customDelimiterMarker.length() + 1), input.substring(customDelimiterMarker.length() + 1) };
        }

    }

    private String parseCustomDelimiter(String input) {
        String delimiter = null;
        if (input == null)
            return defaultDelimiter;
        int delimiterBeginIndex = input.indexOf(multiDelimiterBegin);
        int delimiterEndIndex = input.indexOf(multiDelimiterEnd);
        if (delimiterBeginIndex != -1 && delimiterEndIndex != -1)
            delimiter = input.substring(delimiterBeginIndex + 1, delimiterEndIndex);
        else
            delimiter = input.charAt(2) + "";
        return delimiter;
    }
}
