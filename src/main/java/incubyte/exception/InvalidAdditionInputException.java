package incubyte.exception;

/**
 * Indicates an invalid input String being passed for addition
 * to StringCalculator.Add(String input)
 */
public class InvalidAdditionInputException extends Exception {

    public InvalidAdditionInputException() {

    }

    public InvalidAdditionInputException(String message) {
        super(message);
    }
}
