package apps.utilities;

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
				if (InvalidInputException.isNotValidRange(inputtedValue,
						RANGE_LOWER_BOUND,
						RANGE_UPPER_BOUND)) {
					throw new InvalidInputException("Out of range.");
				}
			} else {
				scanner.next();
				throw new InputMismatchException("Not int.");
			}
		} catch (InvalidInputException | InputMismatchException outOfRangeException) {
			System.out.println(InvalidInputException.returnOutOfBoundsMessage(RANGE_LOWER_BOUND,
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
				if (InvalidInputException.isNotValidLetter(inputtedValue)) {
					throw new InvalidInputException("Includes non-letters.");
				}
			}
		} catch (InvalidInputException invalidInputException) {
			System.out.println(InvalidInputException.returnInvalidLetterMessage());
			inputtedValue = getValidStringInput();
		}
		return inputtedValue;
	}

}
