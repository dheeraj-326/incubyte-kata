package incubyte.util;

import incubyte.exception.InvalidAdditionInputException;

public class StringCalculator {
    public int Add(String numbers) throws InvalidAdditionInputException {
        int sum = 0;
        if (numbers == null)
            throw new InvalidAdditionInputException("InvalidInput: null");
        if (numbers.isEmpty())
            sum = 0;
        else if (!numbers.contains(",")) {
            try {
                sum = Integer.parseInt(numbers);
            } catch (NumberFormatException ne) {
                throw new InvalidAdditionInputException("InvalidInput: Not a number");
            }
        } else {
            String[] parts = numbers.split(",");
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
}
