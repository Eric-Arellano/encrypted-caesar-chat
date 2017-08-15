package inpututilities;

import static inpututilities.MessageTranslator.translateMessage;

public class CommandLineParser {

	public static String parseAndTranslateInputtedMessage(String encryptMode, String message,
	                                                      String key) {
		EncryptMode mode = parseMode(encryptMode);
		validateMessage(message);
		validateKey(key);
		return translateMessage(mode, message, key);
	}

	private static EncryptMode parseMode(String modeValue) {
		EncryptMode mode;
		if (isEncryptionInput(modeValue)) {
			mode = EncryptMode.ENCRYPT;
		} else if (isDecryptionInput(modeValue)) {
			mode = EncryptMode.DECRYPT;
		} else {
			throw new IllegalArgumentException("Invalid mode input.");
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

	private static void validateMessage(String message) {
		if (InvalidInputHelper.containsInvalidLetters(message)) {
			throw new IllegalArgumentException("Invalid message.");
		}
	}

	private static void validateKey(String key) {
		if (InvalidInputHelper.containsInvalidLetters(key)) {
			throw new IllegalArgumentException("Invalid key.");
		}
	}

}
