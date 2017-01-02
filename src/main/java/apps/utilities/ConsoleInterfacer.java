package apps.utilities;

import static apps.utilities.MessageTranslator.EncryptMode;
import static apps.utilities.UserInputParser.getValidIntInput;
import static apps.utilities.UserInputParser.getValidStringInput;

public class ConsoleInterfacer {

	// -----------------------------------------------------------------------------------
	// Choose mode
	// -----------------------------------------------------------------------------------

	public static EncryptMode chooseEncryptOrDecrypt() {
		promptEncryptMode();
		return listenForEncryptMode();
	}

	private static void promptEncryptMode() {
		String instruction = "\nWould you like to encrypt or decrypt your message? Please input \"1\", " +
				"\"2\" or \"0\" to quit.";
		String menuOptions = "\n1)\tEncrypt message\n2)\tDecrypt message";
		System.out.println(instruction + menuOptions);
	}

	private static EncryptMode listenForEncryptMode() {
		final int RANGE_LOWER_BOUND = 0;
		final int RANGE_UPPER_BOUND = 2;
		int userInput = getValidIntInput(RANGE_LOWER_BOUND, RANGE_UPPER_BOUND);
		switch (userInput) {
			case 1:
				return EncryptMode.ENCRYPT;
			case 2:
				return EncryptMode.DECRYPT;
			case 0:
				return EncryptMode.QUIT_APP;
			default:
				return EncryptMode.INVALID;
		}
	}

	// -----------------------------------------------------------------------------------
	// Request message & key
	// -----------------------------------------------------------------------------------

	public static String requestMessage(EncryptMode encryptMode) {
		promptMessage(encryptMode);
		return listenForMessage();
	}

	public static String requestKey(EncryptMode encryptMode) {
		promptKey(encryptMode);
		return listenForKey();
	}

	private static void promptMessage(EncryptMode encryptMode) {
		String prompt = String.format("Please type your %sed message (use lowercase and/or uppercase " +
						"letters, but no spaces or special characters): ",
				MessageTranslator.EncryptMode.getInverseMode(encryptMode));
		System.out.println(prompt);
	}

	private static String listenForMessage() {
		return getValidStringInput();
	}

	private static void promptKey(EncryptMode encryptMode) {
		String prompt = String.format("Please type your %sion key (use a lowercase letter or letters)" +
				": ", encryptMode);
		System.out.println(prompt);
	}

	private static String listenForKey() {
		return getValidStringInput();
	}

	// -----------------------------------------------------------------------------------
	// Print translation
	// -----------------------------------------------------------------------------------

	public static void printNewMessage(EncryptMode encryptMode, String message) {
		String newMessage = String.format("Your new %sed message is: \n\n%s\n\n", encryptMode, message);
		System.out.println(newMessage);
	}
}
