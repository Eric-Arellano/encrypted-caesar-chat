package inpututilities;

import java.util.InputMismatchException;
import java.util.Scanner;

class UserInputParser {

	private static final Scanner scanner = new Scanner(System.in);

	static int getValidIntInput(int RANGE_LOWER_BOUND, int RANGE_UPPER_BOUND) {
		int inputtedValue;
		try {
			if (scanner.hasNextInt()) {
				inputtedValue = scanner.nextInt();
				scanner.nextLine();
				if (InvalidInputHelper.isNotValidRange(inputtedValue,
						RANGE_LOWER_BOUND,
						RANGE_UPPER_BOUND)) {
					throw new InvalidInputHelper("Out of range.");
				}
			} else {
				scanner.next();
				throw new InputMismatchException("Not int.");
			}
		} catch (InvalidInputHelper | InputMismatchException outOfRangeException) {
			System.out.println(InvalidInputHelper.returnOutOfBoundsMessage(RANGE_LOWER_BOUND,
					RANGE_UPPER_BOUND));
			inputtedValue = getValidIntInput(RANGE_LOWER_BOUND, RANGE_UPPER_BOUND);
		}
		return inputtedValue;
	}

	static String getValidStringInput() {
		String inputtedValue = "";
		try {
			if (scanner.hasNextLine()) {
				inputtedValue = scanner.nextLine();
				if (InvalidInputHelper.containsInvalidLetters(inputtedValue)) {
					throw new InvalidInputHelper("Includes non-letters.");
				}
			}
		} catch (InvalidInputHelper invalidInputHelper) {
			System.out.println(InvalidInputHelper.returnInvalidLetterMessage());
			inputtedValue = getValidStringInput();
		}
		return inputtedValue;
	}

}
