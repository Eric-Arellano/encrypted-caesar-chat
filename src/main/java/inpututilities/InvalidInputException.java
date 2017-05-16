package inpututilities;

public class InvalidInputException extends Exception {

	public InvalidInputException(String message) {
		super(message);
	}

	static boolean isNotValidLetter(String message) {
		return !isValidLetter(message);
	}

	private static boolean isValidLetter(String message) {
		for (Character character : message.toCharArray()) {
			if (!Character.isLetter(character)) {
				return false;
			}
		}
		return true;
	}

	static boolean isNotValidRange(int input, int lowerBound, int upperBound) {
		return input < lowerBound || input > upperBound;
	}

	static String returnOutOfBoundsMessage(int lowerBound, int upperBound) {
		return "Oops! Please enter an integer between " + lowerBound + "-" + upperBound + ".";
	}

	static String returnInvalidLetterMessage() {
		return "Oops! Please enter a String with only lowercase and uppercase letters (no numbers, " +
				"spaces, or special characters).";
	}

}
