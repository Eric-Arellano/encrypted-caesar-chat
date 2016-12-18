package userInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

class UserInputHelper {

	private static final Scanner scanner = new Scanner(System.in);

	static int getValidIntInput(int RANGE_LOWER_BOUND, int RANGE_UPPER_BOUND) {
		int inputtedValue;
		try {
			if (scanner.hasNextInt()) {
				inputtedValue = scanner.nextInt();
				scanner.nextLine();
				if (isNotValidRange(inputtedValue, RANGE_LOWER_BOUND, RANGE_UPPER_BOUND)) {
					throw new InvalidInputException("Out of range.");
				}
			} else {
				scanner.next();
				throw new InputMismatchException("Not int.");
			}
		} catch (InvalidInputException | InputMismatchException outOfRangeException) {
			System.out.println(returnOutOfBoundsMessage(RANGE_LOWER_BOUND, RANGE_UPPER_BOUND));
			inputtedValue = getValidIntInput(RANGE_LOWER_BOUND, RANGE_UPPER_BOUND);
		}
		return inputtedValue;
	}

	static String getValidStringInput() {
		String inputtedValue = "";
		try {
			if (scanner.hasNextLine()) {
				inputtedValue = scanner.nextLine();
				if (isNotValidLetter(inputtedValue)) {
					throw new InvalidInputException("Includes non-letters.");
				}
			}
		} catch (InvalidInputException invalidInputException) {
			System.out.println(returnInvalidLetterMessage());
			inputtedValue = getValidStringInput();
		}
		return inputtedValue;
	}

	// ================================================================================
	// Exception handling support
	// ================================================================================

	private static boolean isNotValidRange(int input, int lowerBound, int upperBound) {
		return input < lowerBound || input > upperBound;
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

	private static String returnOutOfBoundsMessage(int lowerBound, int upperBound) {
		return "Oops! Please enter an integer between " + lowerBound + "-" + upperBound + ".";
	}

	private static String returnInvalidLetterMessage() {
		return "Oops! Please enter a String with only lowercase and uppercase letters (no numbers, " +
				"spaces, or special characters).";
	}

	static class InvalidInputException extends Exception {

		InvalidInputException(String message) {
			super(message);
		}
	}

}
