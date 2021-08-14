package incubyte.util;

import incubyte.exception.InvalidAdditionInputException;

public class StringCalculator {
    public int Add(String numbers) throws InvalidAdditionInputException {
        if (numbers == null)
            throw new InvalidAdditionInputException("InvalidInput: null");
        if (numbers.isEmpty())
            return 0;
        if (!numbers.contains(",")) {
            try {
                return Integer.parseInt(numbers);
            } catch (NumberFormatException ne) {
                throw new InvalidAdditionInputException("InvalidInput: Not a number");
            }
        } else {
            String[] parts = numbers.split(",");
            try {
                return Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]);
            } catch (NumberFormatException ne) {
                throw new InvalidAdditionInputException("InvalidInput: Not a number");
            }
        }
    }
}
