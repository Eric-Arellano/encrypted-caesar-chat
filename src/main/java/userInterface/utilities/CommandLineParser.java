package userInterface.utilities;

import static userInterface.utilities.MessageTranslator.EncryptMode;
import static userInterface.utilities.MessageTranslator.translateMessage;

public class CommandLineParser {

	public static String parseAndTranslateInputtedMessage(String encryptMode, String message,
	                                                      String key) {
		EncryptMode mode = parseMode(encryptMode);
		String validatedMessage = validateMessage(message);
		String validatedKey = validateKey(key);
		return translateMessage(mode, validatedMessage, validatedKey);
	}

	private static EncryptMode parseMode(String modeValue) {
		EncryptMode mode = EncryptMode.INVALID;
		try {
			if (isEncryptionInput(modeValue)) {
				mode = EncryptMode.ENCRYPT;
			} else if (isDecryptionInput(modeValue)) {
				mode = EncryptMode.DECRYPT;
			} else {
				throw new InvalidInputException("Invalid mode input.");
			}
		} catch (InvalidInputException invalidInputException) {
			System.out.println("Invalid mode input.");
			System.exit(1);
		}
		return mode;
	}

	private static boolean isEncryptionInput(String modeValue) {
		return modeValue.equalsIgnoreCase("e") ||
				modeValue.equalsIgnoreCase("encrypt") ||
				modeValue.equalsIgnoreCase("encryption") ||
				modeValue.equalsIgnoreCase("-e");
	}

	private static boolean isDecryptionInput(String modeValue) {
		return modeValue.equalsIgnoreCase("d") ||
				modeValue.equalsIgnoreCase("decrypt") ||
				modeValue.equalsIgnoreCase("decryption") ||
				modeValue.equalsIgnoreCase("-d");
	}

	private static String validateMessage(String inputtedMessage) {
		try {
			if (InvalidInputException.isNotValidLetter(inputtedMessage)) {
				throw new InvalidInputException("Invalid message.");
			}
		} catch (InvalidInputException invalidInputException) {
			System.out.println("Invalid message.");
			System.exit(1);
		}
		return inputtedMessage;
	}

	private static String validateKey(String inputtedKey) {
		try {
			if (InvalidInputException.isNotValidLetter(inputtedKey)) {
				throw new InvalidInputException("Invalid key.");
			}
		} catch (InvalidInputException invalidInputException) {
			System.out.println("Invalid key.");
			System.exit(1);
		}
		return inputtedKey;
	}

}
