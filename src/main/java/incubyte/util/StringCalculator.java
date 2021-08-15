package incubyte.util;

import incubyte.exception.InvalidAdditionInputException;

import java.sql.Array;
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
        ArrayList<String> delimiters = parseCustomDelimiters(inputParts[0]);
        String numbers = inputParts[1];
        if (numbers.isEmpty())
            sum = 0;
        else if (!delimiters.stream().anyMatch(delimiter -> numbers.contains(delimiter))) {
            try {
                int number = Integer.parseInt(numbers);
                if (number < 0)
                    throw new InvalidAdditionInputException("negatives not allowed: " + number);
                sum = number > 1000 ? 0 : number;
            } catch (NumberFormatException ne) {
                throw new InvalidAdditionInputException("InvalidInput: Not a number");
            }
        } else {
            List<String> parts = splitByDelimiters(numbers, delimiters);
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

    public List<String> splitByDelimiters(String numbers, ArrayList<String> delimiters) {
        StringBuilder number = new StringBuilder("");
        ArrayList<String> splitNumbers = new ArrayList<>();
        StringBuilder delimiterRegex = new StringBuilder();
        for (int i = 0; i < delimiters.size(); i++) {
            String delimiter = delimiters.get(i);
            if (i != 0)
                delimiterRegex.append("|");
            delimiterRegex.append("(");
            delimiterRegex.append(delimiter);
            delimiterRegex.append(")");
        }
        return Arrays.asList(numbers.split(delimiterRegex.toString()));
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
        int delimiterEndIndex = input.lastIndexOf(multiDelimiterEnd);
        if (delimiterBeginIndex != -1 && delimiterEndIndex != -1) {
            delimiterPart = input.substring(0, delimiterEndIndex + 1);
            inputPart = delimiterEndIndex == input.length() - 1 ? "" : input.substring(delimiterEndIndex + 1);
            return new String[] { delimiterPart, inputPart };
        } else {
            return new String[] { input.substring(0, customDelimiterMarker.length() + 1), input.substring(customDelimiterMarker.length() + 1) };
        }

    }

    private ArrayList<String> parseCustomDelimiters(String input) {
        String delimiter = null;
        ArrayList<String> delimiters = new ArrayList<>();
        if (input == null) {
            delimiters.add(defaultDelimiter);
            return delimiters;
        }
        int delimiterBeginIndex = input.indexOf(multiDelimiterBegin);
        int delimiterEndIndex = input.indexOf(multiDelimiterEnd);
        if (delimiterBeginIndex != -1 && delimiterEndIndex != -1) {
            while (delimiterBeginIndex != -1 && delimiterEndIndex != -1) {
                delimiter = input.substring(delimiterBeginIndex + 1, delimiterEndIndex);
                delimiters.add(delimiter);
                input = input.substring(delimiterEndIndex + 1);
                delimiterBeginIndex = input.indexOf(multiDelimiterBegin);
                delimiterEndIndex = input.indexOf(multiDelimiterEnd);
            }
        } else {
            delimiters.add(input.charAt(2) + "");
        }
        return delimiters;
    }
}
