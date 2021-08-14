package incubyte.util;

import incubyte.exception.InvalidAdditionInputException;

import java.util.ArrayList;
import java.util.Arrays;

public class StringCalculator {

    private final String[] delimiters = new String[] { ",", "\n" };

    public int Add(String numbers) throws InvalidAdditionInputException {
        int sum = 0;
        if (numbers == null)
            throw new InvalidAdditionInputException("InvalidInput: null");
        if (numbers.isEmpty())
            sum = 0;
        else if (!containsDelimiter(numbers)) {
            try {
                sum = Integer.parseInt(numbers);
            } catch (NumberFormatException ne) {
                throw new InvalidAdditionInputException("InvalidInput: Not a number");
            }
        } else {
            ArrayList<String> parts = splitByDelimiter(numbers);
            try {
                for (String part : parts) {
                    sum += Integer.parseInt(part);
                }
            } catch (NumberFormatException ne) {
                throw new InvalidAdditionInputException("InvalidInput: Not a number");
            }
        }
        return sum;
    }

    private ArrayList<String> splitByDelimiter(String input) {
        ArrayList<String> numbers = new ArrayList<>();
        StringBuilder number = new StringBuilder("");
        for (char character : input.toCharArray()) {
            if (Arrays.stream(delimiters).anyMatch(delimiter -> Character.toString(character).equals(delimiter))) {
                numbers.add(number.toString());
                number = new StringBuilder("");
            } else {
                number.append(character);
            }
        }
        numbers.add(number.toString());
        return numbers;
    }

    private boolean containsDelimiter(String input) {
        for (String delimiter : delimiters)
            if (input.contains(delimiter))
                return true;
        return false;
    }
}
