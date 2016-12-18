package userInterface;

import encryptors.Decryptable;
import encryptors.Encryptable;

import static encryptors.CipherChooser.chooseDecryptionCipher;
import static encryptors.CipherChooser.chooseEncryptionCipher;
import static userInterface.UserInputHelper.isNotValidLetter;

public class CommandLineUtilities {

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
				throw new UserInputHelper.InvalidInputException("Invalid mode input.");
			}
		} catch (UserInputHelper.InvalidInputException invalidInputException) {
			System.out.println("Invalid mode input.");
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
			if (isNotValidLetter(inputtedMessage)) {
				throw new UserInputHelper.InvalidInputException("Invalid message.");
			}
		} catch (UserInputHelper.InvalidInputException invalidInputException) {
			System.out.println("Invalid message");
		}
		return inputtedMessage;
	}

	private static String validateKey(String inputtedKey) {
		try {
			if (isNotValidLetter(inputtedKey)) {
				throw new UserInputHelper.InvalidInputException("Invalid message.");
			}
		} catch (UserInputHelper.InvalidInputException invalidInputException) {
			System.out.println("Invalid message");
		}
		return inputtedKey;
	}

	private static String translateMessage(EncryptMode mode, String message, String key) {
		if (isEncryption(mode)) {
			Encryptable encryptor = chooseEncryptionCipher(key);
			return encryptor.encryptMessage(message, key);
		} else {
			Decryptable decryptor = chooseDecryptionCipher(key);
			return decryptor.decryptMessage(message, key);
		}
	}

	private static boolean isEncryption(EncryptMode mode) {
		return mode.equals(EncryptMode.ENCRYPT);
	}

	private enum EncryptMode {
		ENCRYPT, DECRYPT, INVALID;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}
}
